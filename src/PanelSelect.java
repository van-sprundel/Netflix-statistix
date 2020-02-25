import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PanelSelect extends Application implements Initializable {
    private boolean isPopup = false;

    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("Interface/LoginPanel.fxml"));
//        Parent table = FXMLLoader.load(getClass().getResource("Interface/Login.fxml"));
        // Set all panels
        stage.getIcons().add(new Image("/Sprites/Logo.png"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("2160162");
        stage.setMaximized(true);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // All FXML Methods and variables
    @FXML
    private Button signin;
    public TextField email;
    public PasswordField pass;
    public Text error;
    public TextField newName;
    public TextField newEmail;
    public TextField newPass;
    public TextField newAddress;
    public TextField newPostcode;
    public Button signup;
    public Text errorCreate;
    public Text welcomeMsg;

    /* Create account with validation */
    public void setAccount() {
        RWDatabase database = new RWDatabase();
        String name = newName.getText();
        String email = newEmail.getText();
        String pass = newPass.getText();
        String address = newAddress.getText();
        String postalcode = newPostcode.getText();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || address.isEmpty() || postalcode.isEmpty()) {
            errorCreate.setText("Fill in all fields");
        } else if (!email.contains("@") || !email.contains(".")) {
            errorCreate.setText("Invalid email");
        } else {
            errorCreate.setText(" ");
            readAccount(email, pass);
            if (database.validated) {
                errorCreate.setText("This account already exists");
            } else {
                database.setAccount(email, name, address, postalcode, pass);
                database.validated = false;
            }
        }
    }
    /* Check if account is valid */
    public void readAccount(String emailInput, String passInput) {
        RWDatabase database = new RWDatabase();
        database.readAccount(emailInput, passInput);
    }

    /* Loggin button trigger */
    public void login() throws IOException {
        RWDatabase database = new RWDatabase();
        String inputEmail = email.getText();
        String inputPass = pass.getText();
        database.readAccount(inputEmail,inputPass);
        if (email.getText().trim().isEmpty()) {
            error.setText("Please type in your email");
        } else if (pass.getText().trim().isEmpty()) {
            error.setText("Please type in your password");
        } else if (database.validated) {
            Stage stage;
            Parent root;

            if (inputEmail.equals("ramon@gmail.com")) {
                root = FXMLLoader.load(getClass().getResource("Interface/TableView.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("Interface/mainMenu.fxml"));
            }
            stage = (Stage) signin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setMaximized(true);
            stage.show();
            database.validated = false;

        } else { error.setText("Login failed"); }
    }

    /* Popup to create account */
    public  void newUser() throws IOException{
        if (!isPopup) {
            isPopup = true;

            error.setText(" ");
            Stage popupwindow = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Interface/newUser.fxml"));
            Scene scene = new Scene(root);

            popupwindow.setTitle("Create account");
            popupwindow.setScene(scene);
            popupwindow.showAndWait();
            isPopup = false;
        } else {
            error.setText("You're already creating an account");
        }
    }
}
