package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.awt.Component;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class App {
    static final List<String> hardwareTypes = List.of("mainboard", "cpu", "gpu", "ram", "psu", "ssd", "hdd", "ccase",
            "fan", "cpu_cooler", "radiator");
    final GUI gui = new GUI();

    static Map<String, Integer> config = new HashMap<>();
    static final ArrayList<JComboBox<Hardware>> comboboxes = new ArrayList<>(hardwareTypes.size());
    static final ArrayList<ComboBoxSearchable> comboBoxSearchable = new ArrayList<>(hardwareTypes.size());
    static final Map<Integer, Hardware> hardwareList = new HashMap<>();

    final String url = "jdbc:mariadb://localhost:3306/PC_Builder";
    String user = "guest";
    String password = "password";

    public SQLManager sqlManager;
    private ResultSet rs;
    private FilterManager filterManager;

    public App() {
        sqlManager = new SQLManager(url, user, password);
        filterManager = new FilterManager(hardwareTypes, comboboxes, hardwareList);

        try {
            while (!sqlManager.connect()) {
                System.out.println("Program couldn't connect. trying in 5s...");
                Thread.sleep(5000);
            }
            System.out.println("connected successfully");

            for (String hardwareType : hardwareTypes) {
                String query = "SELECT name, price, ID FROM " + hardwareType;
                System.out.println(query);
                rs = SQLManager.getConnection().createStatement().executeQuery(query);

                while (rs.next()) {
                    hardwareList.put(rs.getInt("ID"), new Hardware(rs.getInt("ID"), rs.getString("name"), hardwareType));
                }

                JComboBox<Hardware> cb = new JComboBox<>();

                cb.setToolTipText("Add a " + hardwareType);
                cb.setMaximumSize(cb.getPreferredSize());
                cb.setAlignmentX(Component.CENTER_ALIGNMENT);
                cb.addItemListener(new ChangeHardWare(hardwareType));
                comboBoxSearchable.add(new ComboBoxSearchable(cb));
                GUI.panel.add(cb);

                comboboxes.add(cb);
            }
            System.out.println(hardwareList.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        filterManager.createFilters();
        initConfig();
    }

    public void initConfig() {
        for (String hardware : hardwareTypes) {
            config.put(hardware, 0);
        }
        config.put("wattage", 0);
        config.put("price", 0);
    }

    public static void main(String[] args) {
        new App();
    }

    public static void checkWattage() {
        if (config.get("wattage") > config.get("psu")) {
            JOptionPane.showMessageDialog(null, "PSU Overload",
                    "Your Config's power consumption is higher than your PSU can handle.\nconsider downgrading or using a better PSU",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
