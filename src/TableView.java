import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableView implements Initializable {
    private RWDatabase database = new RWDatabase();
    private LoginPanel loginPanel = new LoginPanel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public Button refresh;

    public void loadData() {
        try {
            Connection connection = DriverManager.getConnection(database.connectionUrl);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Account");
            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                    System.out.println(row);
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

}