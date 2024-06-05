package org.sql;
import java.swing.JCombobox;
import java.swing.Event.ItemListener;
import java.swing.Event.ItemEvent;
public class Account {
  JCombobox<String> combobox;

  public AccountBar() {
    combobox = new JCombobox<String>(new String[] {"Configs", "Login"});

    combobox.addItemListener(new ItemListener() {
      public void ItemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      String opt = (String) e.getItem();
          switch (opt) {
            case "Configs":

              break;
            case "Login/Logout":
            loggedIn ? App.logout() : new Authentication(); 
              break;

            default:
              break;
          }

      }
    });
  }
}
}
