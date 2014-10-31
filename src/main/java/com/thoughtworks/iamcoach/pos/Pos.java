package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.model.CartItem;
import com.thoughtworks.iamcoach.service.ItemService;
import com.thoughtworks.iamcoach.service.ItemServiceImpl;
import com.thoughtworks.iamcoach.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Pos {

    private ArrayList<Item> items = new ArrayList<Item>();;
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();;
    private double sumPrice;
    private double promotionPrice;

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

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
        for (String barcode : barcodes) {
            String[] splitBarcode = barcode.split("-");
            Item item = itemService.getItemByBarcode(splitBarcode[0]);
            items.add(item);
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

    public void caculatePrice() {
        for(CartItem cartItem : cartItems) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            cartItem.setPromotionPrice(cartItem.calculatePromotionPrice());
            sumPrice += cartItem.getSumPrice();
            promotionPrice += cartItem.getPromotionPrice();
        }
    }
}
