package org.sql;

import javax.swing.JComboBox;

public class Account extends JComboBox<String> {
  public Account(String[] opts) {
    super(opts);
    this.addItemListener(e -> {
      switch ((String) e.getItem()) {
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
        case "Register":
          Authentication.register();
          break;
        default:
          break;
      }
    });
  }
}
