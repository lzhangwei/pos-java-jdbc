package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.vo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class PosTest {

    private List<String> createBarcodes() {
        List<String> barcodes = new ArrayList<String>();
        barcodes.add("ITEM000001");
        barcodes.add("ITEM000001");
        barcodes.add("ITEM000001");
        barcodes.add("ITEM000001");
        barcodes.add("ITEM000001");
        barcodes.add("ITEM000003-2");
        barcodes.add("ITEM000005");
        barcodes.add("ITEM000005");
        barcodes.add("ITEM000005");
        return barcodes;
    }

    @Test
    public void parse_barcode_test() {
        List<String> barcodes = createBarcodes();
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        Pos pos = new Pos();
        pos.parseBarcode(barcodes);
        assertThat(pos.getCartItems().size()).isEqualTo(3);
    }

    @Test
    public void calculate_price_test() {
        List<Promotion> promotionList = new ArrayList<Promotion>();
        promotionList.add(new PromotionSecondHalf());
        promotionList.add(new PromotionDiscount());
        promotionList.add(new PromotionTwoForOne());
        Item item1 = new Item(2, "ITEM000001", "雪碧", "瓶", 3.00, new Category(1, "饮料"), promotionList, 80);
        CartItem cartItem1 = new CartItem(item1, 6);
        Item item2 = new Item(1, "ITEM000002", "可乐", "瓶", 3.00, new Category(1, "饮料"), promotionList, 85);
        CartItem cartItem2 = new CartItem(item2, 5);
        ArrayList<CartItem> cartItemList = new ArrayList<CartItem>();
        cartItemList.add(cartItem1);
        cartItemList.add(cartItem2);
        Pos pos = new Pos();
        pos.setCartItems(cartItemList);
        pos.caculatePrice();
        assertThat(pos.getSumPrice()).isEqualTo(33);
        assertThat(pos.getPromotionPrice()).isEqualTo(9);
    }
}
