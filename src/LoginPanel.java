import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPanel extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Set all panels
        Parent login = FXMLLoader.load(getClass().getResource("Interface/currLogin.fxml"));
//        Parent dashboard = FXMLLoader.load(getClass().getResource("Interface/Tableview.fxml"));

        Scene scene = new Scene(login);
        stage.setTitle("studentennummers enzo");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}
