import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PanelSelect extends Application implements Initializable {
    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("Interface/currLogin.fxml"));
//        Parent table = FXMLLoader.load(getClass().getResource("Interface/Login.fxml"));
        // Set all panels

        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("studentennummers enzo");
        stage.setMaximized(true);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    private Button signin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;

    @FXML
    public void setAccount(String email, String pass, String name, String address, String postalcode) {
        ReadDatabase database = new ReadDatabase();
        database.setAccount(email,name,address,postalcode,pass);
    }
    @FXML
    public void getAccount(String email, String pass) {
        ReadDatabase database = new ReadDatabase();
        database.getAccount(email,pass);
    }
    @FXML
    public void login() throws IOException {

        if (email == null) {
            System.out.println("no email");
        } else if (pass == null) {
            System.out.println("no pass");
        } else {
            Stage stage;
            Parent root;
            stage = (Stage) signin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Interface/TableView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
