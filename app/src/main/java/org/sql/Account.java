package org.sql;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class Account extends JComboBox {
  public Account() {
    this.addItemListener(new ItemListener() {
      @Override
      public void ItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          String opt = (String) e.getItem();
          switch (opt) {
            case "Configs":
              if (Authentication.loggedIn)

              break;
            case "Login/Logout":
              Authentication.loggedIn ? Authentication.logout() : Authentication.login();
              break;

            default:
              break;
          }
        }
      }
    });
    }
  }