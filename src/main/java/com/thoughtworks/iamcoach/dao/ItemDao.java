package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.model.Item;

import java.util.ArrayList;

public interface ItemDao {
    ArrayList<Item> getItems();

    Item getItemByBarcode(String barcode);
}
