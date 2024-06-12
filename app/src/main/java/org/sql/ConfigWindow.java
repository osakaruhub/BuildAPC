package org.sql;

import javax.swing.*;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConfigWindow extends JFrame {
    private JTable configTable;
    private Session session;

    public ConfigWindow(Session session) {
        // Create a table to display the configurations
        this.session = session;
        configTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(configTable);
        getContentPane().add(scrollPane);

        ResultSet resultSet = SQLManager.getConfigs(session.getName());

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
        } catch (Exception e) {
        }
        configTable.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int row = configTable.rowAtPoint(e.getPoint());

                if (row >= 0) {

                    int value = (int) configTable.getValueAt(row, 0);

                    System.out.println("Clicked on cell: " + value);

                    // TODO: set current config from chosen saved config
                    ResultSet config = Session.getConfig(value, session.getName() );

                    // App.setConfig(config.toString());
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
            String name = nameField.getText();
            if (!name.equals("")) {
                SQLManager.addConfig(App.config);
            } else {
            }
        }
        return true;
    }



    // ... (save button logic)

    // ... (main method)
}
