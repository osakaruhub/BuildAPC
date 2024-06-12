package org.sql;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class FilterManager {
    private static List<String> hardwareTypes;
    private static ArrayList<JComboBox<Hardware>> comboBoxes;
    private static Map<Integer, Hardware> hardwareList;
    static Map<String, Integer> config = new HashMap<>();
    static ResultSet rs;

    public FilterManager(List<String> hardwareTypes, ArrayList<JComboBox<Hardware>> comboboxes,
            Map<Integer, Hardware> hardwareList) {
        FilterManager.hardwareTypes = hardwareTypes;
        FilterManager.comboBoxes = comboboxes;
        FilterManager.hardwareList = hardwareList;
    }

    public void createFilters() {
        JSlider[] priceFilterSlider = new JSlider[App.hardwareTypes.size()];
        try {
            for (int i = 0; i < priceFilterSlider.length; i++) {
                priceFilterSlider[i] = new JSlider(JSlider.VERTICAL, 0,
                        SQLManager.getConnection().prepareStatement("SELECT MAX("+ App.hardwareTypes.get(i)+".price) as maxPrice FROM "+ hardwareTypes.get(i))
                                .executeQuery().getInt("maxPrice"));
                priceFilterSlider[i]
                        .addChangeListener(new SliderFilter(priceFilterSlider[i].getMaximum(), "cpu", "price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GUI.pricefilterSlider = priceFilterSlider;
        // Add sliders to legend or any other component if needed
    }

    public static void filterByValue(String type, String characteristic, Object value, Boolean out) {
        String query = "SELECT " + type + ".ID FROM " + type + " WHERE " + type + "." + characteristic + " = " + value;
        addFilter(new String[] { query }, type, out, comboBoxes, hardwareTypes, hardwareList);
    }

    public static void filterByItem(String type, int ID, Boolean out) {
        ArrayList<String> queries = new ArrayList<>();
        switch (type) {
            case "cpu":
                queries.add("SELECT m.ID FROM mainboard m WHERE m.cpuForm <> (SELECT cpu.form FROM cpu WHERE cpu.ID = "
                        + ID + ")");
                break;
            case "ram":
                queries.add(
                        "SELECT m.ID FROM mainboard m WHERE m.ddrType <> (SELECT ram.ddrType FROM ram WHERE ram.ID = "
                                + ID + ")");
                queries.add("SELECT cpu.ID FROM cpu WHERE cpu.ddrType < (SELECT ram.ddrType FROM ram WHERE ram.ID = "
                        + ID + ")");
                break;
            case "mainboard":
                queries.add("SELECT cpu.ID FROM cpu WHERE cpu.form <> (SELECT m.form FROM mainboard m WHERE m.ID = "
                        + ID + ")");
                queries.add(
                        "SELECT ram.ID FROM ram WHERE ram.ddrType <> (SELECT m.ddrType FROM mainboard m WHERE m.ID = "
                                + ID + ")");
                queries.add("SELECT c.ID FROM ccase c WHERE c.size < (SELECT m.size FROM mainboard m WHERE m.ID = " + ID
                        + ")");
                break;
            case "ssd":
                queries.add(
                        "SELECT m.ID FROM mainboard m WHERE m.IO NOT LIKE CONCAT('%', (SELECT ssd.type FROM ssd WHERE ssd.ID = "
                                + ID + "), '%')");
                break;
            case "ccase":
                queries.add("SELECT m.ID FROM mainboard m WHERE m.size < (SELECT c.size FROM ccase c WHERE c.ID = " + ID
                        + ")");
                break;
            default:
                break;
        }
        addFilter(queries.toArray(new String[0]), type, out, comboBoxes, hardwareTypes, hardwareList);
    }

    public static void addFilter(String[] queries, String type, Boolean out, ArrayList<JComboBox<Hardware>> comboboxes,
            List<String> hardwareTypes, Map<Integer, Hardware> hardwareList) {
        JComboBox<Hardware> temp = comboboxes.get(hardwareTypes.indexOf(type));
        try {
            if (out) {
                for (String str : queries) {
                    rs = SQLManager.getConnection().prepareStatement(str).executeQuery();
                    while (rs.next()) {
                        temp.removeItem(hardwareList.get(rs.getInt("ID")));
                    }
                }
                comboboxes.set(hardwareTypes.indexOf(type), temp);
            } else {
                HashSet<Hardware> tempHash = new HashSet<>();
                for (int i = 0; i < temp.getItemCount(); i++) {
                    tempHash.add(temp.getItemAt(i));
                }
                for (String str : queries) {
                    rs = SQLManager.getConnection().prepareStatement(str).executeQuery();
                    while (rs.next()) {
                        tempHash.add(hardwareList.get(rs.getInt("ID")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void checkWattage() {
        if (config.get("wattage") > config.get("psu")) {
            JOptionPane.showMessageDialog(null, "PSU Overload",
                    "Your Config's power consumption is higher than your PSU can handle.\nconsider downgrading or using a better PSU",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
