package org.sql;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

/**
 * Authenticaton
 */
public class Authentication {
  static Boolean loggedIn = false;

  private Authentication() {
  }

  static public void login() {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    Object[] message = {
        "Username:", usernameField,
        "Password:", passwordField
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Authentication", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());
      if (username != "" && password != "" && isValidCredentials(username, password)) {
        Authentication.loggedIn = true;
        JOptionPane.showMessageDialog(null, "Authentication successful!");
      } else {
        JOptionPane.showMessageDialog(null, "Invalid username or password.");
      }
    }
  }

  static public void logout() {
    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Authentication",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
      GUI.authenticationButton.setVisible(false);
      Authentication.loggedIn = false;
    }
  }

  private static Boolean isValidCredentials(String username, String password) {
    return SQLManager.getAccount(username) == password.hashCode();
  }
}
