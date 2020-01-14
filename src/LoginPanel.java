import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPanel extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Set all panels
        Parent root = FXMLLoader.load(getClass().getResource("Interface/currLogin.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("Interface/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("studentennummers enzo");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
