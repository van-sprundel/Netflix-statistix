
import javafx.application.Application;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;integratedSecurity=true";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();

//             Class die de sql query bouwt
            String sqlCode = "use test;" +
                             "select * from aa";

            ResultSet rs = statement.executeQuery(sqlCode);

            while (rs.next())
                System.out.println(rs.getString("lol"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Application.launch(LoginPanel.class, args);
    }
}
