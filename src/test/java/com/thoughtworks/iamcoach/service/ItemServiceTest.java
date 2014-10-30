package com.thoughtworks.iamcoach.service;

import com.thoughtworks.iamcoach.vo.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceTest {

    private List<Item> createItems() {
        List<Item> result = new ArrayList<Item>();
        result.add(new Item(0, "ITEM000000", "可口可乐", "瓶", 3.00));
        result.add(new Item(1, "ITEM000001", "雪碧", "瓶", 3.00));
        result.add(new Item(2, "ITEM000002", "苹果", "瓶", 3.00));
        result.add(new Item(3, "ITEM000003", "荔枝", "瓶", 3.00));
        result.add(new Item(4, "ITEM000004", "电池", "瓶", 3.00));
        result.add(new Item(5, "ITEM000005", "方便面", "瓶", 3.00));
        return result;
    }

    @Test
    public void should_be_mocked() {

    }
}
