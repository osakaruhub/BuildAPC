package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.util.List;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class App {
    static final List<String> hardwareTypes = List.of("mainboard", "cpu", "gpu", "ram", "psu", "ssd", "hdd", "ccase",
            "fan", "cpu_cooler", "radiator");
    final GUI gui = new GUI();

    static Map<String, Integer> config = new TreeMap<>();
    static final Map<Integer, Hardware> hardwareList = new HashMap<>();

    static final String url = "jdbc:mariadb://localhost:3306/PC_Builder";
    String user = "guest";
    String password = "password";

    public SQLManager sqlManager;
    private ResultSet rs;
    private FilterManager filterManager;

    public App() {
        sqlManager = new SQLManager(user, password);
        filterManager = new FilterManager(hardwareTypes, gui.comboboxes, hardwareList);

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

                List<Hardware> choices = new ArrayList<>();
                while (rs.next()) {
                    Hardware hardware = new Hardware(rs.getInt("ID"), rs.getString("name"), hardwareType,
                            rs.getLong("price"));
                    hardwareList.put(rs.getInt("ID"), hardware);
                    choices.add(hardware);
                }

                JComboBox<Hardware> cb = new JComboBox<>(choices.toArray(new Hardware[0]));
                cb.setToolTipText("Add a " + hardwareType);
                cb.setPreferredSize(new Dimension(400, 20));
                cb.setMaximumSize(cb.getPreferredSize());
                cb.setAlignmentX(Component.CENTER_ALIGNMENT);
                cb.setSelectedItem(null);
                cb.addItemListener(new ChangeHardWare(hardwareType));
                gui.comboBoxSearchable.add(new ComboBoxSearchable(cb));

                JPanel comboPanel = new JPanel();
                comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));
                comboPanel
                        .add(new JLabel(hardwareType.substring(0, 1).toUpperCase() + hardwareType.substring(1) + ": "));
                comboPanel.add(cb);

                gui.panel.add(comboPanel);
                gui.comboboxes.add(cb);
            }
            System.out.println(hardwareList.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        filterManager.createFilters();
        initConfig();

        gui.frame.setVisible(true);
    }

    public void initConfig() {
        for (String hardWare : hardwareTypes) {
            config.put(hardWare, 0);
        }
        config.put("wattage", 0);
        config.put("price", 0);
    }

    public static void main(String[] args) {
        // if (args[0] != null || args[1] != null && Authentication.isValidCredentials(args[0], args[1])) {
        //     Session.changeSession(args[0], Integer.parseInt(args[1]));
        // }
        new App();
    }
}
