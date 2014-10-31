package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.dao.CategoryDao;
import com.thoughtworks.iamcoach.dao.PromotionDao;
import com.thoughtworks.iamcoach.dao.impl.CategoryDaoImpl;
import com.thoughtworks.iamcoach.dao.impl.ItemDaoImpl;
import com.thoughtworks.iamcoach.dao.ItemDao;
import com.thoughtworks.iamcoach.dao.impl.PromotionDaoImpl;
import com.thoughtworks.iamcoach.model.Category;
import com.thoughtworks.iamcoach.model.Item;
import com.thoughtworks.iamcoach.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private PromotionDao promotionDao = new PromotionDaoImpl();

    @Override
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = itemDao.getItems();
        for(Item item : items) {
            Category category = categoryDao.getCategoryById(item.getCategory().getId());
            item.setCategory(category);

            List<Promotion> promotionList = promotionDao.getPromotionsByItemId(item.getId());
            item.setPromotionList(promotionList);

            item.setDiscount(promotionDao.getPromotionDiscount(item.getId()));
        }
        return items;
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        return itemDao.getItemByBarcode(barcode);
    }
}
