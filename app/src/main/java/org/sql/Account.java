package org.sql;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Account extends JComboBox {
  public Account() {
    this.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String opt = (String) e.getItem();
        switch (opt) {
          case "Configs":
            // if (Authentication.loggedIn) -> GUI.openConfigWindow();

            break;
          case "Login/Logout":
            if (Authentication.loggedIn) {
              Authentication.logout();
            } else {
              Authentication.login();
            }
            break;

          default:
            break;
        }
      }
    });
    }
  }