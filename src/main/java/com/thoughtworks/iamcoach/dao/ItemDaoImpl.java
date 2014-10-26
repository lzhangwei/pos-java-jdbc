package com.thoughtworks.iamcoach.dao;

import com.thoughtworks.iamcoach.util.DBUtil;
import com.thoughtworks.iamcoach.vo.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    private DBUtil dbUtil = new DBUtil();

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
                result.add(new Item(
                        rs.getInt("id"),
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price")
                ));
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

        String sql = "select * from items where barcode=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barcode);
            rs = pstmt.executeQuery(sql);
            rs.next();
            result = new Item(
                    rs.getInt("id"),
                    rs.getString("barcode"),
                    rs.getString("name"),
                    rs.getString("unit"),
                    rs.getDouble("price")
            );
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
