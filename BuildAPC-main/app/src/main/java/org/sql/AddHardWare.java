package org.sql;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * AddHardWare
 */
class AddHardWare implements ItemListener {

  public void itemStateChanged(ItemEvent event) {
    if (event.getStateChange() == ItemEvent.SELECTED) {
      String item = (String)event.getItem();
      System.out.print(App.compatible());
    }
  }
}
