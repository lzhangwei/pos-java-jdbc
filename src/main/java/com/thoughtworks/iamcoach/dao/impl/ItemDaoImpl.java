package com.thoughtworks.iamcoach.dao.impl;

import com.thoughtworks.iamcoach.dao.CategoryDao;
import com.thoughtworks.iamcoach.dao.ItemDao;
import com.thoughtworks.iamcoach.dao.PromotionDao;
import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.model.Category;
import com.thoughtworks.iamcoach.model.Item;
import com.thoughtworks.iamcoach.model.Promotion;

import java.sql.*;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    private DBUtil dbUtil = new DBUtil();
    private PromotionDao promotionDao = new PromotionDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public ArrayList<Item> getItems() {

        ArrayList<Item> result = new ArrayList<Item>();

        Connection conn = dbUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "select * from items";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                Item item = new Item(
                        id,
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price")
                );

                Category category = new Category();
                category.setId(id);
                item.setCategory(category);

                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.close();
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Item getItemByBarcode(String barcode) {

        Item result = null;

        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select items.*,items_promotions.promotionId,items_promotions.discount from items,items_promotions "+
                "where items.barcode=? and items.id=items_promotions.itemId";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barcode);
            rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            result = new Item(id,rs.getString("barcode"),rs.getString("name"),rs.getString("unit"),rs.getDouble("price"));

            int categoryId = rs.getInt("category");
            Category category = categoryDao.getCategoryById(categoryId);
            result.setCategory(category);

            result.setDiscount(rs.getInt("discount"));

            int promotionId = rs.getInt("promotionId");
            Promotion promotion = promotionDao.getPromotionById(promotionId);
            result.getPromotionList().add(promotion);
            while(rs.next()){
                promotionId = rs.getInt("promotionId");
                promotion = promotionDao.getPromotionById(promotionId);
                result.getPromotionList().add(promotion);
            }

            result.setDiscount(promotionDao.getPromotionDiscount(id));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
