package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.vo.Item;

import java.util.ArrayList;

public interface ItemDao {
    ArrayList<Item> getItems();

    Item getItemByBarcode(String barcode);
}
