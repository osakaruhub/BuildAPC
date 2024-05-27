/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.sql;

import java.awt.Component;
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
  static final Map<Integer, Hardware> hardwareList = new HashMap<>();
  JCheckBox[] filterButtons = new JCheckBox[5];
  JSlider[] filterSliders = new JSlider[3];
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
        rs = con.prepareStatement(query).executeQuery();

        String[] choices = new String[rs.getFetchSize()];
        for (String choice : choices) {
          hardwareList.put(rs.getInt("ID"), new Hardware(rs.getInt("ID"), rs.getString("name"), hardwareType));
          choice = rs.getString("name") + "\t" + rs.getLong("buyPrice");
          System.out.println(choice);
          rs.next();
        }

        JComboBox<Hardware> cb = new JComboBox<>();

        cb.setToolTipText("Add a " + hardwareType);

        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.addItemListener(new ChangeHardWare(hardwareType));
        panel.add(cb);

        comboboxes.add(cb);
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    mkfilters();
    frame.add(panel);
    frame.setVisible(true);
    initConfig();
  }

  public void mkfilters() {
    // TODO: Add more filters buttons/sliders
    filterButtons[0] = new JCheckBox("AMD");
    filterButtons[0].addItemListener(new Filter("cpu", "brand", "AMD"));

    filterSliders[0] = new JSlider(JSlider.VERTICAL, 0,
        ps.executeQuery("SELECT MAX(price) as maxPrice FROM *").getInt("maxPrice"));
    filterSliders[0].addChangeListener();

    for (JCheckBox filterButton : filterButtons) {
      panel.add(filterButton);
    }
  }

  static public void filterByValue(String type, String characteristic, Object value, Boolean out) {
    String[] query = {
        "SELECT " + type + ".ID FROM " + type + " WHERE " + type + "." + characteristic + " = " + value };
    addFilter(query, type, out);
  }

  static public void filterByItem(String type, int ID, Boolean out) {
    // TODO: Expand Itemfilters
    ArrayList<String> queries = new ArrayList<>();
    switch (type) {
      case "cpu":

        queries.add("SELECT m.ID FROM mainboard m WHERE m.cpuForm <> (SELECT cpu.form FROM cpu WHERE cpu.ID = " + ID);
        break;

      case "ram":

        queries.add("SELECT m.ID FROM mainboard m WHERE m.ddrType <> (SELECT ram.ddrType FROM cpu WHERE cpu.ID = "
            + ID);
        queries.add("SELECT cpu.ID FROM cpu WHERE cpu.ddrType < (SELECT ram.ddrType FROM cpu WHERE cpu.ID = " + ID);
        break;
      case "mainboard":

        queries.add("SELECT cpu.ID FROM cpu WHERE cpu.form <> (SELECT m.form FROM mainboard m WHERE m.ID = " + ID);
        queries.add("SELECT ram.ID FROM ram WHERE ram.form <> (SELECT m.ddrType FROM mainboard m WHERE m.ID = " + ID);
        queries.add("SELECT case.ID FROM case WHERE case.size < (SELECT m.size FROM mainboard m WHERE m.ID = " + ID);

        break;
      case "ssd":

        queries.add(
            "SELECT m.ID FROM mainboard m WHERE m.IO NOT LIKE CONCAT('%', (SELECT ssd.type FROM ssd WHERE ssd.ID = "
                + ID + "), '%')");
        break;
      case "case":
        queries.add("SELECT m.ID FROM mainboard m WHERE m.size < (SELECT case.size FROM case WHERE case.ID = " + ID);
        break;
      default:
        break;
    }

    addFilter((String[]) queries.toArray(), type, out);
  }

  static public void addFilter(String[] queries, String type, Boolean out) {
    JComboBox<Hardware> temp = comboboxes.get(hardwareTypes.indexOf(type));
    try {
      if (out) {
        for (String str : queries) {
          rs = con.prepareStatement(str).executeQuery();
          for (int i = 0; i < rs.getFetchSize(); i++) {
            temp.removeItem(hardwareList.get(rs.getInt("ID")));
            rs.next();
          }
        }
        comboboxes.set(hardwareTypes.indexOf(type), temp);
      } else {
        HashSet<Hardware> tempHash = new HashSet<>();
        for (int i = 0; i < temp.getItemCount(); i++) {
          tempHash.add(temp.getItemAt(i));
        }
        for (String str : queries) {
          rs = con.prepareStatement(str).executeQuery();
          for (int i = 0; i < rs.getFetchSize(); i++) {
            tempHash.add(hardwareList.get(rs.getInt("ID")));
            rs.next();
          }
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  static public void checkWattage() {
    if (config.get("wattage") > config.get("psu")) {
      JOptionPane.showMessageDialog(null, "PSU Overload",
          "Your Config's power consumption is higher than your PSU can handle.\nconsider downgrading or using a better PSU",
          JOptionPane.INFORMATION_MESSAGE);
      // gui.disableCheckOut();
    }
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

  // public Object get(String hardWare, String attribute) {
  // try {
  // ps = con.prepareStatement("SELECT " + hardWare + "." + attribute +
  // " FROM " + hardWare + " WHERE " + hardWare +
  // ".ID =" + config.get(hardWare));
  // return (ps.executeQuery()).getObject(attribute);
  // } catch (SQLException e) {
  // // TODO: handle exception
  // return null;
  // }
  // }

  public static void main(String[] args) {
    App A = new App();
  }
}
