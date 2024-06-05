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

  public SliderFilter(int maxValue, String type, String characteristic) {
    this.maxValue = maxValue;
    this.type = type;
    this.characteristic = characteristic;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
      int price = (int) source.getValue();
      FilterManager.filterByValue(type, characteristic, price, true);
    }
  }
}
