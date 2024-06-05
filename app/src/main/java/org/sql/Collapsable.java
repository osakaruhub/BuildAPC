package org.sql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Collapsable
 */
public class Collapsable implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    GUI.legend.setVisible(!GUI.legend.isVisible());
    // Adjust the frame size to accommodate the legend panel
  }
}
