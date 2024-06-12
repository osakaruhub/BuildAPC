package org.sql;

import javax.swing.JComboBox;
import java.awt.event.ItemEvent;

public class Account extends JComboBox<String> {
  public Account(String[] opts) {
    super(opts);
    this.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String opt = (String) e.getItem();
        switch (opt) {
          case "Configs":
            if (Authentication.loggedIn) {
              GUI.openConfigWindow();
            }
            break;
          case "Login":
            if (!Authentication.loggedIn && Authentication.login()) {
              this.remove(1);
              this.addItem("Logout");
            }
            break;
          case "Logout":
            if (Authentication.loggedIn && Authentication.logout()) {
              this.remove(1);
              this.addItem("Login");
            }
          default:
            break;
        }
      }
    });
  }
}
