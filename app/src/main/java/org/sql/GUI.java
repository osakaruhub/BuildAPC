package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI {
    static JFrame frame;
    JPanel panel;
    static JPanel legend;
    static JSlider[] pricefilterSlider;
    static JButton checkCompatibilityButton;
    JPanel topPanel;
    Account accountBar;
    static ConfigWindow configWindow;
    JTextField wattage;
    ArrayList<JComboBox<Hardware>> comboboxes;
    static Map<String, ComboBoxSearchable> comboBoxSearchable;

    public GUI() {
        comboboxes = new ArrayList<>();
        comboBoxSearchable = new TreeMap<>();
        init();
    }

    public static void openConfigWindow() {
        configWindow.DisplayConfigs();
        configWindow.setVisible(true);
    }

    // static public void setConfigToBox(Map<String, Integer> config) {
    // for (Map.Entry<String, ComboBoxSearchable> entry :
    // comboBoxSearchable.entrySet()) {
    //
    // String type = entry.getKey();
    //
    // ComboBoxSearchable comboBoxSearchable = entry.getValue();
    //
    // Integer value = hardwareMap.get(type);
    //
    // // Set the cursor value of the ComboBoxSearchable
    //
    // comboBoxSearchable.setCursorValue(value);
    //
    // }
    // }

    private void init() {
        frame = new JFrame("PC Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(topPanel, BorderLayout.NORTH);

        wattage = new JTextField();
        wattage.setText("Your wattage is:\n 0");

        accountBar = new Account(new String[] { "Configs", "Login", "Register" });
        topPanel.add(accountBar);

        checkCompatibilityButton = new JButton("Check Compatibility");
        frame.add(checkCompatibilityButton, BorderLayout.SOUTH);

        configWindow = new ConfigWindow();
    }

}
