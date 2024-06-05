package org.sql;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConfigWindow extends JFrame {
    private JTable configTable;

    public ConfigWindow() {
        // Create a table to display the configurations
        configTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(configTable);
        getContentPane().add(scrollPane);

        // Retrieve configurations from the database
        try {
            // Execute the query to retrieve configurations
            Statement statement = SQLManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_owned_config");

            // Create a table model to hold the configurations
            DefaultTableModel tableModel = new DefaultTableModel();
            configTable.setModel(tableModel);

            // Add columns to the table model
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Value");

            // Add rows to the table model
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String value = resultSet.getString("value");
                tableModel.addRow(new Object[]{id, name, value});
            }

            // Close the database connection
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ... (save button logic)

    // ... (main method)
}