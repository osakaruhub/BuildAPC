/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.sql;

import java.awt.Component;
import java.util.List;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class App {
  static final List<String> hardwareTypes = List.of("mainboard", "cpu", "gpu", "ram", "psu", "storage", "ccase", "fan",
      "cpu_cooler", "rad");
  static final JFrame frame = new JFrame("Simple GUI");
  static final JPanel panel = new JPanel();
  static Map<String, Integer> config = new HashMap<>();
  static final ArrayList<JComboBox<Hardware>> comboboxes = new ArrayList<>(hardwareTypes.size());
  static final ArrayList<Hardware> hardwareList = new ArrayList<>();
  static PreparedStatement ps;
  static Connection con;
  static ResultSet rs;
  final String url = "jdbc:mariadb://localhost:3306/PC_Builder";
  String user = "guest";
  String password = "password";

  public App() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(980, 720);

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    try {
      connect();
      for (String hardwareType : hardwareTypes) {

        String query = "SELECT " + hardwareType + ".name, " + hardwareType +
            ".buyPrice, " + hardwareType + ".ID FROM " + hardwareType;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        String[] choices = new String[rs.getFetchSize()];
        for (String choice : choices) {
          hardwareList.add(new Hardware(rs.getInt("ID"), rs.getString("name")));
          choice = rs.getString("name") + "\t" + rs.getLong("buyPrice");
          System.out.println(choice);
          rs.next();
        }

        JComboBox<Hardware> cb = new JComboBox<>();

        cb.setToolTipText("Add a " + hardwareType);

        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.addItemListener(new AddHardWare(hardwareType));
        panel.add(cb);

        comboboxes.add(cb);
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    frame.add(panel);
    frame.setVisible(true);
    initConfig();
  }

  static public void filterOut(String type, int ID, Boolean out) {
    ArrayList<String> query = new ArrayList<>();
    switch (type) {
      case "cpu":

        query.add("SELECT m.ID FROM mainboard m WHERE m.cpuForm <> (SELECT cpu.form FROM cpu WHERE cpu.ID = " + ID);
        break;

      case "gpu":

        query.add("SELECT m.ID FROM mainboard m WHERE m.cpuForm <> (SELECT cpu.form FROM cpu WHERE cpu.ID = " + ID);
        break;
      case "ram":

        query.add("SELECT m.ID FROM mainboard m WHERE m.ddrType <> (SELECT ram.ddrType FROM cpu WHERE cpu.ID = "
            + ID);
        query.add("SELECT cpu.ID FROM cpu WHERE cpu.ddrType < (SELECT ram.ddrType FROM cpu WHERE cpu.ID = " + ID);
        break;
      case "mainboard":

        query.add("SELECT cpu.ID FROM cpu WHERE cpu.form <> (SELECT m.form FROM mainboard m WHERE m.ID = " + ID);
        query.add("SELECT ram.ID FROM ram WHERE ram.form <> (SELECT m.ddrType FROM mainboard m WHERE m.ID = " + ID);
        query.add("SELECT ram.ID FROM ram WHERE ram.form <> (SELECT m.ddrType FROM mainboard m WHERE m.ID = " + ID);

        break;
      case "ssd":

        query.add(
            "SELECT m.ID FROM mainboard m WHERE m.IO NOT LIKE CONCAT('%', (SELECT ssd.type FROM ssd WHERE ssd.ID = "
                + ID + "), '%')");
        break;
      default:
        break;
    }
    JComboBox<Hardware> temp = comboboxes.get(hardwareTypes.indexOf(type));
    try {
    if (out) {
      for (String str : query) {
        rs = con.prepareStatement(str).executeQuery();
        for (int i = 0; i < rs.getFetchSize(); i++) {
          temp.remove(hardwareList.get());
          rs.next();
        }
      }
      comboboxes.set(hardwareTypes.indexOf(type), temp);
    } else {
    HashSet<Hardware> tempHash = new HashSet<>();
        for (int i = 0; i < temp.getItemCount(); i++) {
          tempHash.add(temp.getItemAt(i));
        }
      for (String str : query) {
        rs = con.prepareStatement(str).executeQuery();
            while (rs.next()) {
    int tempID = rs.getInt("ID");
            try {
    if (!tempHash.contains()) {
        comboBox.addItem(hardwareItem);
        uniqueItems.add(hardwareItem);
    }
}
          temp.removeItemAt(rs.getInt(ID) - 1);
          rs.next();
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  static public void filterIn(String type) {
    // TODO: populate filterIn method

  }

  public static boolean comboBoxContainsItem(JComboBox<Hardware> comboBox, Object item) {
    for (int i = 0; i < comboBox.getItemCount(); i++) {
      if (item.equals(comboBox.getItemAt(i))) {
        return true;
      }
    }
    return false;
  }

  // public String getPassword() {
  // try {
  // return new BufferedReader(new FileReader("passwd.txt")).readLine();
  // } catch (IOException e) {
  // return null;
  // }
  // }

  public void initConfig() {
    for (String hardWare : hardwareTypes) {
      config.put(hardWare, null);
    }
    config.put("wattage", 0);
    config.put("price", 0);
  }

  public Boolean connect() {
    try {
      con = DriverManager.getConnection(url, user, password);
      System.out.println("Connected Successfully!");
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  static public void add(String type, int ID) {
    try {
      ps = con.prepareStatement(
          "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = "
              + config.get("type"));
      config.replace("wattage", config.get("wattage") + (ps.executeQuery()).getInt("wattage"));
      config.replace("price", config.get("price") - (ps.executeQuery()).getInt("price"));
    } catch (SQLException e) {
      // TODO: handle exception
    }
  }

  static public void remove(String type) {
    try {
      ps = con.prepareStatement(
          "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = "
              + config.get("type"));
      config.replace("wattage", config.get("wattage") - (ps.executeQuery()).getInt("wattage"));
      config.replace("price", config.get("price") - (ps.executeQuery()).getInt("price"));
    } catch (SQLException e) {
      // TODO: handle exception
    }
  }

  public Object get(String hardWare, String attribute) {
    try {
      ps = con.prepareStatement("SELECT " + hardWare + "." + attribute +
          " FROM " + hardWare + " WHERE " + hardWare +
          ".ID =" + config.get(hardWare));
      return (ps.executeQuery()).getObject(attribute);
    } catch (SQLException e) {
      // TODO: handle exception
      return null;
    }
  }

  public static void main(String[] args) {
    App A = new App();
  }
}
