package org.sql;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * AddHardWare
 */
class AddHardWare implements ItemListener {
  String type;

  public AddHardWare(String type) {
    this.type = type;
  }

  @Override
  public void itemStateChanged(ItemEvent event) {
    if (event.getStateChange() == ItemEvent.SELECTED) {
      Hardware item = (Hardware) event.getItem();
      if (item != null) {
        int selectedHardwareId = item.getId();
        App.add(type, selectedHardwareId);
        App.filterOut(type, selectedHardwareId);
        App.config.put(type, selectedHardwareId);
      } else {
        App.remove(type);
        App.filterIn(type);
        App.config.put(type, null);
      }
    }
  }
}
