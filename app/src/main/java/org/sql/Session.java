package org.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {
    static private int ID = 0;
    static private String name = "guest";
    static private String email = "guest";
    static private int password = -1;

    private Session() {
    }

    static public void changeSession(String email, int password) {
        Object[] credentials = SQLManager.getCredentials(email, password);
        Session.ID = (int) credentials[0];
        Session.name = (String) credentials[1];
        Session.email = email;
        Session.password = password;

    }

    static public ResultSet getConfig(int id, String user) {
        try {
            return SQLManager.SessionConnection
                    .prepareStatement("SELECT * from user_owned_config WHERE user = " + user + " AND configID = " + id)
                    .executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    static public ResultSet getConfigs(int user) {
        try {
            return SQLManager.SessionConnection
                    .prepareStatement("SELECT id, name from user_owned_config WHERE user = " + user).executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static int getID() {
        return ID;
    }

    public static void setName(String name) {
        Session.name = name;
    }

    public static String getName() {
        return name;
    }
}
