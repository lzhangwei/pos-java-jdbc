package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.service.ItemService;
import com.thoughtworks.iamcoach.service.ItemServiceImpl;
import com.thoughtworks.iamcoach.vo.Item;

import java.util.ArrayList;
import java.util.List;

public class Pos {

    private ArrayList<Item> items;
    private ArrayList<CartItem> cartItems;
    private double sumPrice;
    private double promotionPrice;

    private ItemService itemService = new ItemServiceImpl();

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void parseBarcode(List<String> barcodes) {
        cartItems = new ArrayList<CartItem>();

        for (String barcode : barcodes) {
            String[] splitBarcode = barcode.split("-");
            Item item = itemService.getItemByBarcode(splitBarcode[0]);
            int num = splitBarcode.length == 1 ? 1 : Integer.parseInt(splitBarcode[1]);
            cartItems.add(new CartItem(item, num));
        }

        cartItems = mergeCartItems(cartItems);
    }

    private ArrayList<CartItem> mergeCartItems(ArrayList<CartItem> cartItems) {
        for (int i = 0; i < cartItems.size() - 1; i++) {
            for (int j = i + 1; j < cartItems.size(); j++) {
                if (cartItems.get(i).getBarcode().equals(cartItems.get(j).getBarcode())) {
                    double newNum = cartItems.get(i).getNum() + cartItems.get(j).getNum();
                    cartItems.get(i).setNum(newNum);
                    cartItems.remove(j);
                    i--;
                    break;
                }
            }
        }
        return cartItems;
    }

    private ArrayList<CartItem> clone(ArrayList<CartItem> cartItems) {
        ArrayList<CartItem> result = new ArrayList<CartItem>();
        for(CartItem cartItem : cartItems) {
            result.add((CartItem)cartItem.clone());
        }
        return result;
    }

    public ArrayList<CartItem> calFreePromotion(List<String> freeBarcodes) {
        ArrayList<CartItem> result = clone(cartItems);
        for (CartItem cartItem : result) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            if (isPromotionBarcode(cartItem, freeBarcodes)) {
                double promotionPrice = (int) (cartItem.getNum() / 3) * cartItem.getPrice();
                cartItem.setPromotionPrice(promotionPrice);
            }
        }
        return result;
    }

    private boolean isPromotionBarcode(CartItem cartItem, List<String> freeBarcodes) {
        for (String barcode : freeBarcodes) {
            if (cartItem.getBarcode().equals(barcode)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CartItem> calHalfPromotion(List<String> freeBarcodes) {
        ArrayList<CartItem> result = clone(cartItems);
        for (CartItem cartItem : result) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            if (isPromotionBarcode(cartItem, freeBarcodes)) {
                cartItem.setPromotionPrice((int) (cartItem.getNum() / 2) * cartItem.getPrice() / 2);
            }
        }
        return result;
    }

    public ArrayList<CartItem> calDiscountPromotion(List<String> freeBarcodes) {
        ArrayList<CartItem> result = clone(cartItems);
        for (CartItem cartItem : result) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            String barcode = hasDiscountPromotionBarcode(cartItem, freeBarcodes);
            if(barcode != null) {
                String[] barcodes = barcode.split(":");
                double discount = (100 - Integer.parseInt(barcodes[1])) / 100.0;
                cartItem.setPromotionPrice(cartItem.getNum() * cartItem.getPrice() * discount);
            }
        }
        return result;
    }

    private String hasDiscountPromotionBarcode(CartItem cartItem, List<String> freeBarcodes) {
        String result = null;
        for (String barcode : freeBarcodes) {
            String[] barcodes = barcode.split(":");
            if (cartItem.getBarcode().equals(barcodes[0])) {
                result = barcode;
            }
        }
        return result;
    }

    public double calSumPrice(ArrayList<CartItem> cartItems) {
        double result = 0;
        for (CartItem cartItem : cartItems) {
            result += cartItem.getSumPrice();
        }
        return result;
    }

    public double calSumPromotionPrice(ArrayList<CartItem> cartItems) {
        double result = 0;
        for (CartItem cartItem : cartItems) {
            result += cartItem.getPromotionPrice();
        }
        return result;
    }

    public void comparePrice(ArrayList<CartItem> cartItems1, ArrayList<CartItem> cartItems2, ArrayList<CartItem> cartItems3) {
        cartItems = cartItems1;
        sumPrice = calSumPrice(cartItems1);
        promotionPrice = calSumPromotionPrice(cartItems1);
        if (calSumPromotionPrice(cartItems2) > promotionPrice) {
            cartItems = cartItems2;
            promotionPrice = calSumPromotionPrice(cartItems2);
        }
        if (calSumPromotionPrice(cartItems3) > promotionPrice) {
            cartItems = cartItems3;
            promotionPrice = calSumPromotionPrice(cartItems3);
        }
    }

    public void caculatePrice() {
        for(CartItem cartItem : cartItems) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            cartItem.setPromotionPrice(cartItem.calculatePromotionPrice());
            sumPrice += cartItem.getSumPrice();
            promotionPrice += cartItem.getPromotionPrice();
        }
    }
}
