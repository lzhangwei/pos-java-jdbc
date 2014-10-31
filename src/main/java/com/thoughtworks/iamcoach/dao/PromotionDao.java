package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.model.Promotion;

import java.util.List;

public interface PromotionDao {
    public Promotion getPromotionById(int id);
    public List<Promotion> getPromotions();
    public List<Promotion> getPromotionsByItemId(int id);
    public int getPromotionDiscount(int id);
}
