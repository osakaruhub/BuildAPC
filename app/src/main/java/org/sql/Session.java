package org.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Session {
    static private String name = "guest";
    static private String password = "password";
    private ArrayList<Map<String,Integer>> configs = new ArrayList<>();
    static public SQLManager sqlManager = new SQLManager(name, password);

    private Session() { sqlManager.connect(); }

    static public void changeSession(String name, String password){
        setName(name);
        setPassword(password);
        sqlManager.connect();
    }

    static public ResultSet getConfig(int id, String user) {
        try {
            return SQLManager.SessionConnection.prepareStatement("SELECT * from user_owned_config WHERE user = "+ user +" AND configID = "+ id).executeQuery() ;
        } catch (SQLException e) {
            return null;
        }
    }

    static public ResultSet getConfigs(int user) {
        try {
            return SQLManager.SessionConnection.prepareStatement("SELECT id, name from user_owned_config WHERE user = "+ user).executeQuery() ;
        } catch (SQLException e ) {
            return null;
        }
    }

    static public void setPassword(String password) {
        Session.password = password;
    }

    static public void setName(String name) {
        Session.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
