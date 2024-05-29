package org.sql;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Filter
 */
public class Filter implements ItemListener {

  String type;
  String filter;
  Object value;

  public Filter(String type, String filter, Object value) {
    this.filter = filter;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    App.filterByValue(type, filter, value, e.getStateChange() == ItemEvent.SELECTED);
  }
}
