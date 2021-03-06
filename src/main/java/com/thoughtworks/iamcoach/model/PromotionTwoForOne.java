package com.thoughtworks.iamcoach.model;

public class PromotionTwoForOne extends Promotion {
    @Override
    public double caculatePromotionPrice(Item item, double num) {
        return (int)num / 3 * item.getPrice();
    }
}
