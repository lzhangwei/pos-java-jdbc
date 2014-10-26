package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.vo.Item;

import java.util.ArrayList;

public interface ItemService {
    ArrayList<Item> getItems();

    Item getItemByBarcode(String barcode);
}
