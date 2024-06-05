package org.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLManager {
    private static Connection con;
    private static PreparedStatement ps;
    private String url;
    private String user;
    private String password;
    private static HashMap<String, Integer> config = new HashMap<>();

    public SQLManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Boolean connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static public Connection getConnection() {
        return con;
    }

    static public void add(String type, int ID) {
        try {
            ps = con.prepareStatement(
                "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("wattage");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) + wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void remove(String type, int ID) {
        try {
            ps = con.prepareStatement(
                "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("wattage");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) - wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public int getAccount(String username) {
        try {
            ps = con.prepareStatement("SELECT password FROM user WHERE name = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                return password.hashCode();
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
