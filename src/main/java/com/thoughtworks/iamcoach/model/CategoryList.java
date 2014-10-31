package com.thoughtworks.iamcoach.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    private Category category;
    private List<CartItem> cartItemList = new ArrayList<CartItem>();

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
    }
}
