package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.dao.ItemDaoImpl;
import com.thoughtworks.iamcoach.vo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    private ArrayList<Item> createItems() {
        Category category = new Category(1,"饮料");
        List<Promotion> promotionList = new ArrayList<Promotion>();
        promotionList.add(new PromotionDiscount());
        promotionList.add(new PromotionSecondHalf());
        ArrayList<Item> result = new ArrayList<Item>();
        result.add(new Item(0, "ITEM000000", "可口可乐", "瓶", 3.00, category, promotionList));
        result.add(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00, category, promotionList));
        result.add(new Item(2, "ITEM000002", "苹果", "瓶", 3.00, category, promotionList));
        result.add(new Item(3, "ITEM000003", "荔枝", "瓶", 3.00, category, promotionList));
        result.add(new Item(4, "ITEM000004", "电池", "瓶", 3.00, category, promotionList));
        result.add(new Item(5, "ITEM000005", "方便面", "瓶", 3.00, category, promotionList));
        return result;
    }

    @Test
    public void get_item_by_barcode_test() {
        ItemDaoImpl itemDaoImpl = mock(ItemDaoImpl.class);
        when(itemDaoImpl.getItemByBarcode("ITEM000001")).thenReturn(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00));
        ItemService itemService = new ItemServiceImpl();
        assertThat(itemService.getItemByBarcode("ITEM000001").getId()).isEqualTo(1);
    }

    @Test
    public void get_items_test() {
        ItemDaoImpl itemDaoImpl = mock(ItemDaoImpl.class);
        when(itemDaoImpl.getItems()).thenReturn(createItems());
        ItemService itemService = new ItemServiceImpl();
        assertThat(itemService.getItems().size()).isEqualTo(6);
    }
}
