package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.dao.impl.ItemDaoImpl;
import com.thoughtworks.iamcoach.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    private ArrayList<Item> createItems() {
        Category category1 = new Category(1, "饮料");
        Category category2 = new Category(2, "水果");
        List<Promotion> promotionList = new ArrayList<Promotion>();
        promotionList.add(new PromotionDiscount());
        promotionList.add(new PromotionSecondHalf());
        ArrayList<Item> result = new ArrayList<Item>();
        result.add(new Item(1, "ITEM000000", "可口可乐", "瓶", 3.00, category1, promotionList, 90));
        result.add(new Item(2, "ITEM000001", "雪碧", "瓶", 3.00, category1, promotionList, 85));
        result.add(new Item(3, "ITEM000002", "苹果", "瓶", 3.00, category2, promotionList, 95));
        result.add(new Item(4, "ITEM000003", "荔枝", "瓶", 12.00, category2, promotionList, 80));
        result.add(new Item(2, "ITEM000001", "雪碧", "瓶", 3.00, category1, promotionList, 85));
        result.add(new Item(3, "ITEM000002", "苹果", "瓶", 8.00, category2, promotionList, 95));
        return result;
    }

    @Test
    public void get_item_by_barcode_test() {
        ItemDaoImpl itemDaoImpl = mock(ItemDaoImpl.class);

        List<Promotion> promotionList = new ArrayList<Promotion>();
        promotionList.add(new PromotionSecondHalf());
        Item item = new Item(2, "ITEM000001", "雪碧", "瓶", 3.00, new Category(1, "饮料"), promotionList, 100);

        when(itemDaoImpl.getItemByBarcode("ITEM000001")).thenReturn(item);
        ItemService itemService = new ItemServiceImpl();
        assertThat(itemService.getItemByBarcode("ITEM000001").getId()).isEqualTo(2);
    }

    @Test
    public void get_items_test() {
        ItemDaoImpl itemDaoImpl = mock(ItemDaoImpl.class);
        ArrayList<Item> items = createItems();
        when(itemDaoImpl.getItems()).thenReturn(items);
        ItemService itemService = new ItemServiceImpl();
        assertThat(itemService.getItems().size()).isEqualTo(6);
        assertThat(itemService.getItems().get(3).getName()).isEqualTo("荔枝");
    }
}
