package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.vo.Item;

import java.util.ArrayList;

/**
 * Created by zhangwei on 14-10-26.
 */
public interface ItemDao {
    ArrayList<Item> getItems();

    Item getItemByBarcode(String barcode);
}
