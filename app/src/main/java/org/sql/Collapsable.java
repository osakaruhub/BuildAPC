package org.sql;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Collapsable
 */
public class Collapsable implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    App.legend.setVisible(!App.legend.isVisible());
    // Adjust the frame size to accommodate the legend panel
  }
}
