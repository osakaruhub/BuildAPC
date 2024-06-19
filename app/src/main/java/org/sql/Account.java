package org.sql;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
          JOptionPane.showMessageDialog(null,
              Authentication.register() ? "Successfully Registered!" : "Registration failed", "Register",
              JOptionPane.DEFAULT_OPTION);
          break;
        default:
          break;
      }
    });
  }
}
