import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TVLoader extends Application implements Initializable {
    private RWDatabase database = new RWDatabase();
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();

    private final StringProperty name;

    public TVLoader(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @FXML
    public Button refresh;

//    public void loadData() {
//        System.out.println("Loading data...");
//        try {
//            ObservableList<String> row = FXCollections.observableArrayList();
//
//            Connection connection = DriverManager.getConnection(database.connectionUrl);
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("USE [Netflix Statistix Database];" +
//                                                       "SELECT * FROM Account");
//            while (rs.next()) {
//               for (int i = 1; i < 7; i++) {
//                   row.add((rs.getString(i)));
//                }
//                  System.out.println("test");
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//
//    }


    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}