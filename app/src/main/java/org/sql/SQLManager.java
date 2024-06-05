import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLManager {
    private Connection con;
    private PreparedStatement ps;
    private String url = "jdbc:mysql://localhost:3306/yourdatabase"; // Update with your database URL
    private String user = "yourusername"; // Update with your database username
    private String password = "yourpassword"; // Update with your database password
    private static HashMap<String, Integer> config = new HashMap<>();

    public Boolean connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void add(String type, int ID) {
        try {
            ps = con.prepareStatement(
                "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("wattage");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) + wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String type, int ID) {
        try {
            ps = con.prepareStatement(
                "SELECT " + type + ".wattage, " + type + ".price FROM " + type + " WHERE " + type + ".ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int wattage = rs.getInt("wattage");
                int price = rs.getInt("price");
                config.put("wattage", config.getOrDefault("wattage", 0) - wattage);
                config.put("price", config.getOrDefault("price", 0) - price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAccount(String username) {
        try {
            ps = con.prepareStatement("SELECT password FROM user WHERE name = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                return password.hashCode();
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
