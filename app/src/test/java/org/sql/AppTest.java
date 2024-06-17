/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.client.result.ResultSetMetaData;
import java.sql.SQLException;

class AppTest {
  App classUnderTest = new App();

  @Test
  void appConnection() {
    assertTrue(classUnderTest.sqlManager.connect());
    System.out.println("Connection successful");
  }

  void FilterByItem() {
    for (String type : App.hardwareTypes) {
      for (int i = 0; i < 20; i++) {
        assertTrue(FilterManager.filterByItem(type, i, true));
      }
    }
  }

  void filterByValue() {
    try {
      for (String type : App.hardwareTypes) {
        ResultSetMetaData rsmd = (ResultSetMetaData) SQLManager.getConnection()
            .prepareStatement("SELECT ID FROM " + type).executeQuery().getMetaData();
        for (int i = 1; i > rsmd.getColumnCount(); i++) {
          FilterManager.filterByValue(type, rsmd.getColumnName(1), 1, true);
        }

      }
    } catch (SQLException e) {
      // TODO: handle exception
    }
  }

  // @Test
  // void appComboboxes() {
  // }

}
