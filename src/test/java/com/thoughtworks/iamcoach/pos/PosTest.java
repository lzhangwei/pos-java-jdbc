package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.vo.Item;
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
    public void calculate_free_promotion_test() {
        List<String> barcodes = createBarcodes();
        List<String> freeBarcodes = new ArrayList<String>();
        freeBarcodes.add("ITEM000001");
        freeBarcodes.add("ITEM000003");
        freeBarcodes.add("ITEM000005");
        Pos pos = new Pos();
        pos.parseBarcode(barcodes);
        ArrayList<CartItem> result = pos.calFreePromotion(freeBarcodes);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getSumPrice()).isEqualTo(15);
        assertThat(result.get(0).getPromotionPrice()).isEqualTo(3);
    }

    @Test
    public void calculate_half_promotion_test() {
        List<String> barcodes = createBarcodes();
        List<String> freeBarcodes = new ArrayList<String>();
        freeBarcodes.add("ITEM000001");
        freeBarcodes.add("ITEM000003");
        freeBarcodes.add("ITEM000005");
        Pos pos = new Pos();
        pos.parseBarcode(barcodes);
        ArrayList<CartItem> result = pos.calHalfPromotion(freeBarcodes);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getSumPrice()).isEqualTo(15);
        assertThat(result.get(0).getPromotionPrice()).isEqualTo(3);
    }

    @Test
    public void calculate_discount_promotion_test() {
        List<String> barcodes = createBarcodes();
        List<String> freeBarcodes = new ArrayList<String>();
        freeBarcodes.add("ITEM000001:75");
        freeBarcodes.add("ITEM000003:85");
        freeBarcodes.add("ITEM000005:90");
        Pos pos = new Pos();
        pos.parseBarcode(barcodes);
        ArrayList<CartItem> result = pos.calDiscountPromotion(freeBarcodes);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getSumPrice()).isEqualTo(15);
        assertThat(result.get(0).getPromotionPrice()).isEqualTo(3.75);
    }

    @Test
    public void caculate_sum_price() {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        cartItems.get(0).setSumPrice(15);
        cartItems.get(1).setSumPrice(30);
        cartItems.get(2).setSumPrice(7.5);
        Pos pos = new Pos();
        double result = pos.calSumPrice(cartItems);
        assertThat(result).isEqualTo(52.5);
    }

    @Test
    public void caculate_sum_promotion_price() {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        cartItems.get(0).setPromotionPrice(3);
        cartItems.get(1).setPromotionPrice(0);
        cartItems.get(2).setPromotionPrice(2.5);
        Pos pos = new Pos();
        double result = pos.calSumPromotionPrice(cartItems);
        assertThat(result).isEqualTo(5.5);
    }

    @Test
    public void compare_sum_price() {
        ArrayList<CartItem> cartItems1 = new ArrayList<CartItem>();
        cartItems1.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems1.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems1.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        cartItems1.get(0).setSumPrice(15);
        cartItems1.get(1).setSumPrice(30);
        cartItems1.get(2).setSumPrice(7.5);
        cartItems1.get(0).setPromotionPrice(3);
        cartItems1.get(1).setPromotionPrice(0);
        cartItems1.get(2).setPromotionPrice(2.5);
        ArrayList<CartItem> cartItems2 = new ArrayList<CartItem>();
        cartItems2.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems2.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems2.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        cartItems1.get(0).setSumPrice(15);
        cartItems1.get(1).setSumPrice(30);
        cartItems1.get(2).setSumPrice(7.5);
        cartItems2.get(0).setPromotionPrice(3);
        cartItems2.get(1).setPromotionPrice(3.5);
        cartItems2.get(2).setPromotionPrice(1.25);
        ArrayList<CartItem> cartItems3 = new ArrayList<CartItem>();
        cartItems3.add(new CartItem(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00), 5));
        cartItems3.add(new CartItem(new Item(3, "ITEM000003", "荔枝", "千克", 15.00), 2));
        cartItems3.add(new CartItem(new Item(5, "ITEM000005", "方便面", "袋", 2.50), 3));
        cartItems1.get(0).setSumPrice(15);
        cartItems1.get(1).setSumPrice(30);
        cartItems1.get(2).setSumPrice(7.5);
        cartItems3.get(0).setPromotionPrice(3.75);
        cartItems3.get(1).setPromotionPrice(4.5);
        cartItems3.get(2).setPromotionPrice(0.75);
        Pos pos = new Pos();
        pos.comparePrice(cartItems1,cartItems2,cartItems3);
        assertThat(pos.getSumPrice()).isEqualTo(52.5);
        assertThat(pos.getPromotionPrice()).isEqualTo(9);
    }
}
