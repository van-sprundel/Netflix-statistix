import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    private RWDatabase database = new RWDatabase();
    private ObservableList<ObservableList> dataAcc = FXCollections.observableArrayList();
    private ObservableList<ObservableList> dataSeries = FXCollections.observableArrayList();
    private ObservableList<ObservableList> dataMovies = FXCollections.observableArrayList();
    private ObservableList<ObservableList> dataProf = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML

    public TableView aAccounts;
    public TableView sAccounts;
    public TableView mAccounts;

    public TableView aProfiles;
    public TableView sProfiles;
    public TableView mProfiles;

    public TableView aSeries;
    public TableView sSeries;

    public TableView aMovies;
    public TableView mMovies;


    public void loadData(String dataType, ObservableList obsType, TableView tableType, String exSQL) {
        // Clearing Table
        tableType.getColumns().clear();
        tableType.getItems().clear();
        obsType.clear();
        // Loading data
        System.out.println("Loading data...");
        try {

            Connection connection = DriverManager.getConnection(database.connectionUrl);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("USE [Netflix Statistix Database];" +
                    "SELECT * FROM " + dataType + exSQL);
            // Add Columns dynamically, counter starts at 1 to skip identifiers
            for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                tableType.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                // Adds data to the ObservableList
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                obsType.add(row);
            }
            System.out.println(obsType);
            tableType.setItems(obsType);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void refresh() {
        loadData("Account", dataAcc, aAccounts, "");
        loadData("Account", dataAcc, sAccounts, "");
        loadData("Account", dataAcc, mAccounts, "");

        loadData("Series", dataSeries, aSeries, "");
        loadData("Series", dataSeries, sSeries, "");

        loadData("Movies", dataMovies, aMovies, "");
        loadData("Movies", dataMovies, mMovies, "");

//        loadData("Profile", dataProf,profiles);
    }

    public void selProfile() {
//        Object list = accounts.getSelectionModel().selectedItemProperty().get();
        Object list = aAccounts.getSelectionModel().getFocusedIndex();

        System.out.println(list);
//        loadData("Profile",dataProf,profiles,"WHERE Email = ");

    }

}
