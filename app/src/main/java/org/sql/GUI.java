package org.sql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GUI extends JFrame {

    JCheckBox[] filterButtons = new JCheckBox[5];
    // Map<String, JSlider[]> filterSliders;
    JSlider[] PriceFilterSlider;
    static final JPanel panel = new JPanel();
    static final JPanel legend = new JPanel();
    static final JPanel headerPanel = new JPanel();
    static final JButton authenticationButton = new JButton("Authenticate"), collapseButton = new JButton();

    public GUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(980, 720);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        legend.setPreferredSize(new Dimension(100, this.getHeight()));
        headerPanel.setLayout(new BorderLayout());

        headerPanel.add(authenticationButton, BorderLayout.EAST);

        legend.setLayout(new FlowLayout(FlowLayout.CENTER));

        collapseButton.addActionListener(new Collapsable());

        headerPanel.add(collapseButton, BorderLayout.WEST);
        this.add(headerPanel, BorderLayout.NORTH);

        this.add(panel);
        this.add(legend, BorderLayout.WEST);

        filterButtons[0] = new JCheckBox("AMD");
        filterButtons[0].addItemListener(new Filter("cpu", "brand", "AMD"));

        legend.add(filterButtons[0]);

        this.setVisible(true);
        this.pack(); // sets size of the frame automaticly
        this.setTitle("PC-Builder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void openConfigWindows() {

    }
}
