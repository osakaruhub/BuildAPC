package org.sql;

import javax.swing.JCheckBox;

/**
 * CheckBox
 */
public class CheckBox extends JCheckBox {
  String type;
  String filter;
  Object value;

  public CheckBox(String type, String filter, Object value) {
    super();
    this.type = type;
    this.filter = filter;
    this.value = value;
  }
}
