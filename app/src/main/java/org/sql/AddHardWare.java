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
        App.filter(type, selectedHardwareId, true);
        App.config.put(type, selectedHardwareId);
      } else {
        App.remove(type);
        App.filter(type, App.config.get(type), false);
        App.config.put(type, null);
      }
    }
  }
}
