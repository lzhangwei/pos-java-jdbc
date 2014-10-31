package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.model.Item;

import java.util.ArrayList;

public interface ItemService {
    ArrayList<Item> getItems();

    Item getItemByBarcode(String barcode);
}
