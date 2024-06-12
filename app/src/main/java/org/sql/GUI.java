package org.sql;

import com.jidesoft.swing.ComboBoxSearchable;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class GUI {
    JPanel panel;
    static JPanel legend;
    JPanel topPanel;
    JButton configButton;
    Account user;
    ArrayList<JComboBox<Hardware>> comboboxes;
    ArrayList<ComboBoxSearchable> comboBoxSearchable;

    public GUI() {
        comboboxes = new ArrayList<>();
        comboBoxSearchable = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        configButton = new JButton("Configs");
        user = new Account({"Configs","Login/Logout"});
        topPanel.add(user);
    }

    public void createFilters() {
        JSlider[] priceFilterSlider = new JSlider[comboboxes.size()];
        try {
            for (int i = 0; i < priceFilterSlider.length; i++) {
                priceFilterSlider[i] = new JSlider(JSlider.VERTICAL, 0,
                        SQLManager.getConnection().prepareStatement("SELECT MAX(cpu.price) as maxPrice FROM cpu")
                                .executeQuery().getInt("maxPrice"));
                priceFilterSlider[i]
                        .addChangeListener(new SliderFilter(priceFilterSlider[i].getMaximum(), "cpu", "price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Add sliders to legend or any other component if needed
    }
}
