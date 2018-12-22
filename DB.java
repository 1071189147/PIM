/***@author16130110069王啸林*/

import java.sql.*;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
public class DB {
    static final String DB_NAME = "test";
    static final String DB_USER = "root";
    static final String DB_PW = "123456";

    static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME+"?serverTimezone=GMT";
    static {
        try {
            LoadDriver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void LoadDriver() throws ClassNotFoundException {//链接数据库
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
    }

    public static ResultSet execSQL(String sql, Connection c) throws SQLException {//执行查询语句
        Statement s = c.createStatement();
        return s.executeQuery(sql);
    }

    public static boolean exec(String sql, Connection c) throws SQLException {//执行其他操作
        Statement s = c.createStatement();
        return s.execute(sql);
    }



}
