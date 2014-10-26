package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.dao.ItemDaoImpl;
import com.thoughtworks.iamcoach.dao.ItemDao;
import com.thoughtworks.iamcoach.vo.Item;

import java.util.ArrayList;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public ArrayList<Item> getItems() {
        return itemDao.getItems();
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        return itemDao.getItemByBarcode(barcode);
    }
}
