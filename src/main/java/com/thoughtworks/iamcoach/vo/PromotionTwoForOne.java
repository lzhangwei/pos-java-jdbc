package com.thoughtworks.iamcoach.vo;

public class PromotionTwoForOne extends Promotion {
    @Override
    public double caculatePromotionPrice(Item item, double num) {
        return num / 3 * item.getPrice();
    }
}
