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
    JTextArea wattage;
    static JList<String> reccomendations;
    ArrayList<JComboBox<Hardware>> comboboxes;
    static Map<String, ComboBoxSearchable> comboBoxSearchable;
static JLabel wattageLabel;
static JLabel priceLabel;

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
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        wattageLabel = new JLabel(String.format("Wattage: %d", 0));

        priceLabel = new JLabel(String.format("price: %d", 0));

        topPanel.add(wattageLabel, BorderLayout.EAST);
        topPanel.add(priceLabel, BorderLayout.EAST);

        accountBar = new Account(new String[] { "Configs", "Login", "Register" });
        topPanel.add(accountBar);
        frame.add(topPanel, BorderLayout.NORTH);

        checkCompatibilityButton = new JButton("Check Compatibility");
        frame.add(checkCompatibilityButton, BorderLayout.SOUTH);

        configWindow = new ConfigWindow();
    }

    
    static public void updatePriceLabel() {
        priceLabel.setText(String.format("Price: %d", App.config.get("wattage")));
    }

    static public void updateWattageLabel() {
        wattageLabel.setText(String.format("Wattage: %d", App.config.get("wattage")));
    }
}
