package com.thoughtworks.iamcoach.vo;

public class PromotionSecondHalf extends Promotion {
    @Override
    public double caculatePromotionPrice(Item item, double num) {
        return (int)num / 2 * (item.getPrice() / 2.0);
    }
}
