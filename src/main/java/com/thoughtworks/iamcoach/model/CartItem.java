package com.thoughtworks.iamcoach.model;

import com.thoughtworks.iamcoach.model.Item;
import com.thoughtworks.iamcoach.model.Promotion;

import java.util.List;

public class CartItem{
    private Item item;
    private double num;
    private double sumPrice;
    private double promotionPrice;

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public CartItem(Item item, double num) {
        this.item = item;
        this.num = num;
    }

    public double getPrice() {
        return item.getPrice();
    }

    public String getBarcode() {
        return item.getBarcode();
    }

    public double calculatePromotionPrice() {
        double result = 0;
        List<Promotion> promotionList = item.getPromotionList();
        if (promotionList.size() > 0) {
            result = promotionList.get(0).caculatePromotionPrice(item, num);
            for (int i = 1; i < promotionList.size(); i++) {
                double price = promotionList.get(i).caculatePromotionPrice(item, num);
                result = price > result ? price : result;
            }
        }
        return result;
    }
}
