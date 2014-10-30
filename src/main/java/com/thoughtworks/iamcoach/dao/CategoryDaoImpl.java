package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.vo.Category;
import com.thoughtworks.iamcoach.vo.Promotion;
import com.thoughtworks.iamcoach.vo.PromotionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao{

    private DBUtil dbUtil = new DBUtil();

    @Override
    public Category getCategoryById(int id) {
        Category category = null;
        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM categories WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery(sql);
            rs.next();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
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
        return category;
    }
}
