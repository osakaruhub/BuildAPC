package org.sql;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Authenticaton
 */
public class Authentication {
  static Boolean loggedIn = false;

  private Authentication() {
  }

  static public Boolean login() {
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    Object[] message = {
        "Email:", emailField,
        "Password:", passwordField
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
      String email = emailField.getText();
      String password = new String(passwordField.getPassword());
      if (email != "" && password != "" && isValidCredentials(email, password)) {
        Authentication.loggedIn = true;
        JOptionPane.showMessageDialog(null, "Authentication successful!");
        Session.changeSession(email, password.hashCode());
      } else {
        JOptionPane.showMessageDialog(null, "Invalid username or password.");
        return false;
      }
    } else {
      return false;
    }
    return true;
  }

  static public Boolean logout() {
    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Authentication",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
      Session.changeSession("guest", 0);
      Authentication.loggedIn = false;
      return true;
    } else {
      return false;
    }
  }

  public static Boolean register() {
    JTextField usernameField = new JTextField();
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    Object[] message = {
        "Username:", usernameField,
        "Email:", emailField,
        "Password:", passwordField
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Register", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      // && !Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
      // emailField.getText())) {
      // // check if email is pattern "*@*.*"
      // return false;
      // }
      try {
        return SQLManager.register(usernameField.getText(), emailField.getText(),
            new String(passwordField.getPassword()));
      } catch (EmailAlreadyExistsException e) {
        JOptionPane.showMessageDialog(null, "Email Already Exists!", "Error", JOptionPane.OK_OPTION);
        return false;
      }
    }
    return false;
  }

  public static Boolean isValidCredentials(String email, String password) {
    return SQLManager.getAccount(email) == password.hashCode();
  }
}
