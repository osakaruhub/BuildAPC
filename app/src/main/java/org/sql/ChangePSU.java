package org.sql;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangePSU extends ChangeHardWare {
    String type;

    public ChangePSU(String type) {
        super(type);
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
                FilterManager.checkWattage();
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
