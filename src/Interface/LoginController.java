package Interface;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField userField;
    @FXML private TextField passField;
    @FXML private Button loginButton;

    public void initialize() {}

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                String sessionID = authorize();
                if (sessionID != null) {
                    loginManager.authenticated(sessionID);
                }
            }
        });
    }
}
