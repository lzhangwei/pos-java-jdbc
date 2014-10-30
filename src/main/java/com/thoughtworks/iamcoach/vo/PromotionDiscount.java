package com.thoughtworks.iamcoach.vo;

public class PromotionDiscount extends Promotion {
    @Override
    public double caculatePromotionPrice(Item item, double num) {
        return num * item.getPrice() * ((100 - item.getDiscount())) / 100.0;
    }
}
