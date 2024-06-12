package org.sql;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeHardWare implements ItemListener {
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
                int previousHardwareId = App.config.get(type);
                SQLManager.remove(type, previousHardwareId);
                FilterManager.filterByItem(type, previousHardwareId, false);
                App.config.put(type, 0);
            }
            FilterManager.checkWattage();
        }
    }
}
