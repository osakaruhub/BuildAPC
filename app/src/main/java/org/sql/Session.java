package org.sql;

import java.util.ArrayList;
import java.util.Map;

public class Session {
    static private String name = "guest";
    static private String password = "password";
    private ArrayList<Map<String,Integer>> configs = new ArrayList<>();
    public SQLManager sqlManager = new SQLManager(name, password);

    public Session() {
        sqlManager.connect();
    }

    static public void changeSession(String name, String password){
        setName(name);
        setPassword(password);
    }

    public ArrayList<Map<String, Integer>> getConfigs() {
        sqlManager.connect();
        return configs;
    }

    static public void setPassword(String password) {
        this.password = password;
    }

    static public void setName(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
