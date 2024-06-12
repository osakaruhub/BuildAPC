package org.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLManager {
    private static Connection con;
    public static Connection SessionConnection;
    private static PreparedStatement ps;
    private String user;
    private String password;
    private static HashMap<String, Integer> config = new HashMap<>();

    public SQLManager(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Boolean connect() {
        try {
            if (user.equals("guest")) {
                con = DriverManager.getConnection(App.url, user, password);
            } else {
                SessionConnection = DriverManager.getConnection(App.url, user, password);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static public Connection getConnection() {
        return con;
    }

    static public Boolean addConfig(Map<String, Integer> config) {
        String add = "INSERT INTO TABLE user_owned_config VALUES (";

        for (int i = 0; i < App.hardwareTypes.size(); i++) {

            add += config.getOrDefault(App.hardwareTypes.get(i), null) + ",";
        }
        add.substring(0, add.length() - 1);
        /*
         * for (int hardware:config
         * ) {
         * add += hardware + ",";
         * }
         */
        add += ");";
        try {
            SQLManager.getConnection().prepareCall(add).executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    static public ResultSet getConfigs(String user) {
        try {
            return getConnection().prepareStatement(
                    ("SELECT * FROM user_owned_config WHERE userID = (SELECT userID from user WHERE name = " + user
                            + " )"))
                    .executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    static public void add(String type, int ID) {
        try {
            ps = con.prepareStatement(
                    "SELECT " + (type.equals("fan") || type.equals("gpu") ? type + ".tdp," : "") + type
                            + ".price FROM " + type
                            + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("tdp");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) + wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void remove(String type, int ID) {
        try {
            ps = con.prepareStatement(
                    "SELECT " + (type.equals("cpu") || type.equals("gpu") ? type + ".tdp," : "") + type
                            + ".price FROM " + type
                            + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("tdp");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) - wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
            ps.close();
            rs.close();
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
                ps.close();
                rs.close();
                return password.hashCode();
            } else {
                ps.close();
                rs.close();
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
