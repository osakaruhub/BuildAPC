package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class GUI {
    static JFrame frame;
    JPanel panel;
    static JPanel legend;
    static JSlider[] pricefilterSlider;
    static JButton checkCompatibilityButton;
    JPanel topPanel;
    Account user;
    static ConfigWindow configWindow;
    ArrayList<JComboBox<Hardware>> comboboxes;
    ArrayList<ComboBoxSearchable> comboBoxSearchable;

    public GUI() {
        comboboxes = new ArrayList<>();
        comboBoxSearchable = new ArrayList<>();
        init();
    }

    public static void openConfigWindow() {
        configWindow.DisplayConfigs();
        configWindow.setVisible(true);
    }

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
        frame.add(topPanel, BorderLayout.NORTH);

        user = new Account(new String[] { "Configs", "Login", "Register" });
        topPanel.add(user);

        checkCompatibilityButton = new JButton("Check Compatibility");
        frame.add(checkCompatibilityButton, BorderLayout.SOUTH);

        configWindow = new ConfigWindow();
    }

}
