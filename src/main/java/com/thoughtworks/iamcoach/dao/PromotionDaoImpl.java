package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.vo.Promotion;
import com.thoughtworks.iamcoach.vo.PromotionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDaoImpl implements PromotionDao{
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
            rs = pstmt.executeQuery(sql);
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
            while(rs.next()) {
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
}
