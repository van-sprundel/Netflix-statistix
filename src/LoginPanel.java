import Interface.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPanel extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginController = new LoginController();
        Parent login = FXMLLoader.load(getClass().getResource("Interface/currLogin.fxml"));
//        Parent table = FXMLLoader.load(getClass().getResource("Interface/Login.fxml"));
        // Set all panels
//        Parent dashboard = FXMLLoader.load(getClass().getResource("Interface/Tableview.fxml"));
//        if (loginController.success) {
//            stage.setScene(new Scene(table));
//        }
        Scene scene = new Scene(login);
        stage.setTitle("studentennummers enzo");
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();


    }
}
