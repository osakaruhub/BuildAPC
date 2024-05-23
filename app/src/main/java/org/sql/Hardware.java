package org.sql;

/**
 * Hardware
 */
public class Hardware {
  private int id;
  private String name;

  public Hardware(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return name;
  }
}
