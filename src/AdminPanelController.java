import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
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

public class AdminPanelController implements Initializable {
    private RWDatabase database = new RWDatabase();
    private ObservableList<ObservableList> dataAcc = FXCollections.observableArrayList();
    private ObservableList<ObservableList> dataSeries = FXCollections.observableArrayList();
    private ObservableList<ObservableList> dataEpisode = FXCollections.observableArrayList();
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

    public TableView sEpisode;

    public TabPane tabPane;

    public Button btnClose;
    public Button btnMax;
    public Button btnMin;

    double x, y;
    int selectedTable; //1 account, 2 profile, 3 series, 4 episodes, 5 movies
    String pos; // position of id of table row

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
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
                    "SELECT * FROM " + dataType + " " + exSQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
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
                obsType.add(row);
            }
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

        loadData("Episode", dataEpisode, sEpisode, "");

//        loadData("Profile", dataProf,profiles);
    }


    public String selData() {
        if (selectedTable == 1) {
            System.out.println("Listing accounts");
            if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Accounts")) {
                return aAccounts.getSelectionModel().getSelectedItem().toString();
            } else if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Series")) {
                return sAccounts.getSelectionModel().getSelectedItem().toString();
            } else {
                return mAccounts.getSelectionModel().getSelectedItem().toString();
            }
        } else if (selectedTable == 2) {
            System.out.println("Listing profiles");
            if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Accounts")) {
                return aProfiles.getSelectionModel().getSelectedItem().toString();
            } else if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Series")) {
                return sProfiles.getSelectionModel().getSelectedItem().toString();
            } else {
                return mProfiles.getSelectionModel().getSelectedItem().toString();
            }
        } else if (selectedTable == 3) {
            System.out.println("Listing series");
            if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Accounts")) {
                return aSeries.getSelectionModel().getSelectedItem().toString();
            } else {
                return sSeries.getSelectionModel().getSelectedItem().toString();
            }
        } else if (selectedTable == 4) {
            System.out.println("Listing episodes");
            return sEpisode.getSelectionModel().getSelectedItem().toString();
        } else {
            System.out.println("Listing movies");
            if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Accounts")) {
                return aMovies.getSelectionModel().getSelectedItem().toString();
            } else {
                return mMovies.getSelectionModel().getSelectedItem().toString();
            }
        }
    }

    public void delData() {
        if (selectedTable == 1) {
            database.delAccount(pos);
            System.out.println("Deleting account with ID: " + pos);
        } else if (selectedTable == 2) {
            database.delProfile(pos);
            System.out.println("Deleting profile with ID: " + pos);
        } else if (selectedTable == 3) {
            database.delSerie(pos);
            System.out.println("Deleting serie with ID: " + pos);
        } else if (selectedTable == 4) {
            database.delEpisode(pos);
            System.out.println("Deleting episode with ID: " + pos);
        } else {
            database.delMovie(pos);
            System.out.println("Deleting movie with ID: " + pos);
        }
        refresh();
    }

    public void selAccTable() {
        selectedTable = 1;
        // Splits data into before and after comma
        String splitted[] = selData().split(",", 2);
        // Gets left section and removes bracket
        pos = splitted[0].replace("[", "");
        System.out.println("Position: " + pos);

        loadData("Profile", dataProf, aProfiles, "WHERE AccountID = " + pos);
        loadData("Profile", dataProf, sProfiles, "WHERE AccountID = " + pos);
        loadData("Profile", dataProf, mProfiles, "WHERE AccountID = " + pos);
    }

    private void getPos() {
        // Splits data into before and after comma
        String splitted[] = selData().split(",", 2);
        // Gets left section and removes bracket
        pos = splitted[0].replace("[", "");
        System.out.println(pos);
    }

    public void selProTable() {
        selectedTable = 2;
        getPos();
        if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Movies")) {
            loadData("WatchedMovies RIGHT JOIN Movies ON WatchedMovies.MovieID = Movies.MovieID", dataMovies, mMovies, " WHERE ProfileID = " + pos);
        }
    }

    public void selSerTable() {
        selectedTable = 3;
        getPos();
        if (tabPane.getSelectionModel().getSelectedItem().getText().equals("Series")) {
            loadData("Episode", dataEpisode, sEpisode, " WHERE SerieID = " + pos);
        }
    }

    public void selEpiTable() {
        selectedTable = 4;
        getPos();
    }

    public void selMovTable() {
        selectedTable = 5;
        getPos();
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

    public void openRep() throws IOException {
        try {
            java.awt.Desktop.getDesktop().browse(new URI("http://github.com/ramones156"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // All FXML Methods and variables
    @FXML
    public void openCreateAccount() throws IOException {
        TabMonitor tabMonitor = new TabMonitor();
        tabMonitor.newUser();
    }
}
