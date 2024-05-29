package org.sql;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;

/**
 * SliderFilter
 */
public class SliderFilter implements ChangeListener {

  int maxValue;
  String type;
  String characteristic;
  Object value;

  @Override
public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      int price = (int) source.getValue();
      if (price == ) {
        
      }
      App.filterByValue(type, characteristic, value, true);
    }
  }
}
