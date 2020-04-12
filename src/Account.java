import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Account {

    @FXML
    public TextField newFullname;
    public TextField newUsername;
    public TextField newEmail;
    public TextField newPass;
    public TextField newAddress;
    public TextField newPostcode;
    public Button signup;
    public Text errorCreate;

    /* Check if account is valid */
    public static void readAccount(String emailInput, String passInput) {
        DatabaseAPI database = new DatabaseAPI();
        database.checkAccount(emailInput, passInput);
    }

    /* Sign up with validation */
    public void setAccount() {
        DatabaseAPI database = new DatabaseAPI();
        String fullname = newFullname.getText();
        String username = newUsername.getText();
        String email = newEmail.getText();
        String pass = newPass.getText();
        String address = newAddress.getText();
        String postalCode = newPostcode.getText();

        if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || pass.isEmpty() || address.isEmpty() || postalCode.isEmpty()) {
            errorCreate.setText("Fill in all fields");
        } else if (!email.contains("@") || !email.contains(".")) {
            errorCreate.setText("Invalid email");
        } else {
            errorCreate.setText(" ");
            Account.readAccount(email, pass);
            if (database.validated) {
                errorCreate.setText("This account already exists");
            } else {
                database.setAccount(email, username, fullname, address, postalCode, pass);
                Stage stage = (Stage) signup.getScene().getWindow();
                stage.close();
            }
        }
    }
}
