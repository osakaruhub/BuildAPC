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
      String item = (String) event.getItem();
      Hardware selectedHardware = (Hardware) hardwareComboBox.getSelectedItem();
      if (selectedHardware != null) {
        int selectedHardwareId = selectedHardware.getId();
        App.config.put(type, selectedHardwareId);
        App.filterOut(selectedHardwareId);
        App.changeValues(selectedHardwareId);
      } else {
        App.config.put(type, null);
        App.filterIn(type);
        App.changeValues(type);
      }
    }
  }
}
