package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.vo.Category;

import java.sql.*;

public class CategoryDaoImpl implements CategoryDao{

    private DBUtil dbUtil = new DBUtil();

    @Override
    public Category getCategoryById(int id) {
        Category category = new Category();
        Connection conn = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM categories WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
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
