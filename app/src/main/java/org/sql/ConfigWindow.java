package org.sql;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConfigWindow extends JFrame {
    private JTable configTable;
    private Session user;

    public ConfigWindow(Session session) {
        // Create a table to display the configurations
        this.session = session;
        configTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(configTable);
        getContentPane().add(scrollPane);

        ArrayList<Map<String,Integer>> = getConfigs();


            DefaultTableModel tableModel = new DefaultTableModel();
            configTable.setModel(tableModel);

            tableModel.addColumn("ID");
            tableModel.addColumn("Name");


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                tableModel.addRow(new Object[]{id, name,});
            }

            resultSet.close();
            statement.close();
    }

    // ... (save button logic)

    // ... (main method)
}
