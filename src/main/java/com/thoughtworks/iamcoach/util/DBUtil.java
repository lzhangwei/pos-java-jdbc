package com.thoughtworks.iamcoach.util;

import java.sql.*;

public class DBUtil {

    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/letusgo?useUnicode=true&&characterEncoding=utf-8";
    private String user = "zhangwei";
    private String password = "123";

    private Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
