package com.thoughtworks.iamcoach.model;

public abstract class Promotion {
    private int  id;
    private String promotionDesc;
    private int type;

    public Promotion() {
    }

    public Promotion(int id, String promotionDesc, int type) {
        this.id = id;
        this.promotionDesc = promotionDesc;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public abstract double caculatePromotionPrice(Item item, double num);
}
