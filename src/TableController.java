import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    private RWDatabase database = new RWDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public Button refresh;
    public TableView Accounts;

    public void loadData() {
        System.out.println("Loading data...");
        ObservableList<ObservableList> rows = FXCollections.observableArrayList();

        try {

            Connection connection = DriverManager.getConnection(database.connectionUrl);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("USE [Netflix Statistix Database];" +
                    "SELECT * FROM Account");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                rows.add(row);
            }
            System.out.println(rows);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
