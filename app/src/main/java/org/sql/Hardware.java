package org.sql;

/**
 * Hardware
 */
public class Hardware {
  private int id;
  private String name;
  private String type;

  public Hardware(int id, String name, String type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return name;
  }
}
