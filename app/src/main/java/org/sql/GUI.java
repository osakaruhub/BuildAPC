package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class GUI {
    JPanel panel;
    static JPanel legend;
    static JSlider[] pricefilterSlider;
    JButton checkCompatibilityButton;
    JPanel topPanel;
    Account user;
    ArrayList<JComboBox<Hardware>> comboboxes;
    ArrayList<ComboBoxSearchable> comboBoxSearchable;

    public GUI() {
        comboboxes = new ArrayList<>();
        comboBoxSearchable = new ArrayList<>();
        init();
    }

    public static void openConfigWindow() {
        new ConfigWindow();
    }

    private void init() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        user = new Account(new String[] {"Configs","Login/Logout"});
        topPanel.add(user);

        checkCompatibilityButton = new JButton("Check Compatibility");
    }


}
