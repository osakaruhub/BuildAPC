package org.sql;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ConfigWindow extends JFrame {
    private JTable configTable;

    public ConfigWindow() {
        JScrollPane scrollPane = new JScrollPane(configTable);
        getContentPane().add(scrollPane);
        GUI.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public void DisplayConfigs() {

        configTable = new JTable();

        ResultSet resultSet = SQLManager.getConfigs(Session.getName());

        DefaultTableModel tableModel = new DefaultTableModel();
        configTable.setModel(tableModel);

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                tableModel.addRow(new Object[] { id, name, });
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        configTable.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int row = configTable.rowAtPoint(e.getPoint());

                if (row >= 0) {

                    int value = (int) configTable.getValueAt(row, 0);

                    System.out.println("Clicked on cell: " + value);

                    // TODO: set current config from chosen saved config
                    ResultSet config = Session.getConfig(value, Session.getName());

                    Map<String, Integer> configd = new TreeMap<String, Integer>();
                    try {
                        for (String type : App.hardwareTypes) {
                            configd.put(type, config.getInt(type));
                        }
                    } catch (SQLException SQL) { // TODO: handle exception }
                        App.setConfig(configd);
                    }

                }
            }
        });
    }

    public Boolean save() {
        JTextField nameField = new JTextField();

        Object[] message = {
                "name:", nameField,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Authentication", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Boolean cancel = true;
            do {
                String name = nameField.getText();
                if (name.equals("")) {
                    cancel = JOptionPane.showConfirmDialog(null, "name cannot be empty!", "Error",
                            JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION;
                }
            } while (!cancel);
        }
        return true;
    }
}
