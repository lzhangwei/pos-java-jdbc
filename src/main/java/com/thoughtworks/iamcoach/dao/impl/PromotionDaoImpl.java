package com.thoughtworks.iamcoach.dao.impl;

import com.thoughtworks.iamcoach.dao.PromotionDao;
import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.model.Promotion;
import com.thoughtworks.iamcoach.model.PromotionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDaoImpl implements PromotionDao {
    private DBUtil dbUtil = new DBUtil();

    @Override
    public Promotion getPromotionById(int id) {
        Promotion promotion = null;
        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM promotions WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int type = rs.getInt("type");
            promotion = PromotionFactory.getPromotionByType(type);
            promotion.setId(rs.getInt("id"));
            promotion.setPromotionDesc(rs.getString("prodesc"));
            promotion.setType(rs.getInt("type"));
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
        return promotion;
    }

    @Override
    public List<Promotion> getPromotions() {
        List<Promotion> promotions = new ArrayList<Promotion>();
        String sql = "SELECT * FROM promotions";
        Connection conn = dbUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int type = rs.getInt("type");
                Promotion promotion = PromotionFactory.getPromotionByType(type);
                promotion.setId(rs.getInt("proid"));
                promotion.setPromotionDesc(rs.getString("prodesc"));
                promotion.setType(rs.getInt("type"));
                promotions.add(promotion);
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
        return promotions;
    }

    @Override
    public List<Promotion> getPromotionsByItemId(int id) {
        List<Promotion> promotionList = new ArrayList<Promotion>();
        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM items_promotions WHERE itemId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int promotionId = rs.getInt("promotionId");
                int discount = rs.getInt("discount");
                Promotion promotion = getPromotionById(promotionId);
                promotionList.add(PromotionFactory.getPromotionByType(promotion.getType()));
            }
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
        return promotionList;
    }

    @Override
    public int getPromotionDiscount(int id) {
        int result = 100;
        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT discount FROM items_promotions WHERE itemId=? AND promotionId=3";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("discount");
            }
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
