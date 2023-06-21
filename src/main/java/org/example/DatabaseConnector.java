package org.example;

import java.sql.*;

public class DatabaseConnector {
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }    public Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/Bookstore";
            String username = "root";
            String password = "1971";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }
    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            String sql = "SELECT * FROM books";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(
                        rs.getInt("BookID") + "  " +
                        rs.getString("title") + "   " +
                        rs.getString("author") + "  " +
                        rs.getString("PublicationYear") + "   " +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

