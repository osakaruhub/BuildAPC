package org.sql;

public class Hardware {
    private int id;
    private String name;
    private String type;
    private long price;

    public Hardware(int id, String name, String type, long price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
