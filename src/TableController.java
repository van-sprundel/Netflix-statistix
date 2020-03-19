import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    public Button btnClose;
    public Button btnMax;
    public Button btnMin;

    double x, y;
    int selectedTable; //1 account, 2 profile, 3 series, 4 movies
    private String dataType;
    private ObservableList obsType;
    private TableView tableType;
    private String exSQL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void loadData(String dataType, ObservableList obsType, TableView tableType, String exSQL) {
        // Clearing Table
        tableType.getColumns().clear();
        tableType.getItems().clear();
        obsType.clear();
        // Loading data
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
            // sets table data to ObservableList
            tableType.setItems(obsType);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void refresh() {
        System.out.println("Loading data...");

        loadData("Account", dataAcc, aAccounts, "");
        loadData("Account", dataAcc, sAccounts, "");
        loadData("Account", dataAcc, mAccounts, "");

        loadData("Series", dataSeries, aSeries, "");
        loadData("Series", dataSeries, sSeries, "");

        loadData("Movies", dataMovies, aMovies, "");
        loadData("Movies", dataMovies, mMovies, "");

//        loadData("Profile", dataProf,profiles);
    }


    public String selData() {
        if (selectedTable == 1) {
            System.out.println("Listing accs");
            return aAccounts.getSelectionModel().getSelectedItem().toString();
        }
        else if (selectedTable == 3) {
            System.out.println("Listing series");
            return aSeries.getSelectionModel().getSelectedItem().toString();
        } else {
            System.out.println("Listing movies");
            return aMovies.getSelectionModel().getSelectedItem().toString();
        }
    }

    public void delData() {
        // Splits data into before and after comma
        String splitted[] = selData().split(",", 2);
        // Gets left section and removes bracket
        String pos = splitted[0].replace("[", "");
        if (selectedTable == 1) {
            database.delAccount(pos);
            System.out.println("Deleting account with ID: " + pos);
        } else if (selectedTable == 3) {
            database.delSerie(pos);
            System.out.println("Deleting serie with ID: " + pos);
        } else {
            database.delMovie(pos);
            System.out.println("Deleting movie with ID: " + pos);
        }

        refresh();
    }

    public void addProfile() {
    }

    public void openRep() throws IOException {
        try {
            java.awt.Desktop.getDesktop().browse(new URI("http://github.com/ramones156"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void selAccTable() {
        selectedTable = 1;
    }
    public void selProTable() {
        selectedTable = 2;
    }
    public void selSerTable() {
        selectedTable = 3;
    }
    public void selMovTable() {
        selectedTable = 4;
    }

    // Menu section
    public void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void max() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());

    }

    public void min() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleClickAction(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void handleMovementAction(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        if (!stage.isMaximized()) {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        }

    }



}
