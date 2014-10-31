package com.thoughtworks.iamcoach.pos;

import com.thoughtworks.iamcoach.vo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class CartItemTest {
    @Test
    public void caculate_promotion_price_test() {
        List<Promotion> promotionList = new ArrayList<Promotion>();
        promotionList.add(new PromotionSecondHalf());
        promotionList.add(new PromotionDiscount());
        promotionList.add(new PromotionTwoForOne());
        Item item = new Item(2, "ITEM000001", "雪碧", "瓶", 3.00, new Category(1, "饮料"), promotionList, 80);

        CartItem cartItem = new CartItem(item, 6);
        assertThat(cartItem.calculatePromotionPrice()).isEqualTo(6);
    }
}
