package org.sql;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * AddHardWare
 */
class ChangeHardWare implements ItemListener {
  String type;

  public ChangeHardWare(String type) {
    this.type = type;
  }

  @Override
  public void itemStateChanged(ItemEvent event) {
    if (event.getStateChange() == ItemEvent.SELECTED) {
      Hardware item = (Hardware) event.getItem();
      if (item != null) {
        int selectedHardwareId = item.getId();
        SQLManager.add(type, selectedHardwareId);
        FilterManager.filterByItem(type, selectedHardwareId, true);
        App.config.put(type, selectedHardwareId);
      } else {
        SQLManager.remove(type);
        FilterManager.filterByItem(type, App.config.get(type), false);
        App.config.put(type, null);
      }
      App.checkWattage();
    }
  }
}
