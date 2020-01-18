package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public boolean success = false;
    // Default scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean success() {
        return success;
    }
    @FXML
    public void login() {
//        success = true;
    }
}
