package org.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    "SELECT " + (type.equals("cpu") || type.equals("gpu") ? type + ".tdp," : "") + type
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

    static public Object[] getCredentials(String email, int password) {
        if (getAccount(email) != password) {
            return null;
        }
        try {
            ResultSet Id = ps.executeQuery("SELECT userID, name FROM user WHERE email = " + email);
            return new Object[] { Id.getInt("userID"), Id.getString("name") };

        } catch (SQLException e) {
            return null;
        }
    }

    static public int getAccount(String email) {
        try {
            ps = con.prepareStatement("SELECT password FROM user WHERE email = " + email);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int password = rs.getInt("password");
                ps.close();
                rs.close();
                return password;
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

    static public Boolean register(String username, String email, String password) throws EmailAlreadyExistsException {
        try {
            PreparedStatement checkEmails = con
                    .prepareStatement("SELECT COUNT(*) FROM user WHERE email ='" + email + "'");
            checkEmails.setString(1, email);
            ResultSet checkEmail = checkEmails.executeQuery();
            checkEmail.next();
            if (checkEmail.getInt(1) == 0) {
                throw new EmailAlreadyExistsException();
            }

            ps = con.prepareStatement("INSERT INTO user (username, email, password) VALUES (" + username + "," + email
                    + "," + password.hashCode() + ")");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
