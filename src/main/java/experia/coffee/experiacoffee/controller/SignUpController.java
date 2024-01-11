package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        System.out.println("Signup page controllore initialized");
    }

    @FXML
    private AnchorPane signUpAnchorPane;

    /* TEXT FIELDS */

    @FXML
    public TextField name;

    @FXML
    public TextField surname;

    @FXML
    public TextField email;

    @FXML
    public TextField pwd;

    @FXML
    public TextField repeatPwd;

    @FXML
    public TextField phoneNumber;

    @FXML
    public TextField streetAddress;

    @FXML
    public TextField streetNumber;

    @FXML
    public TextField postalCode;

    @FXML
    public TextField city;

    /* BUTTONS */

    @FXML
    public Button confirmSubscription;

    @FXML
    public Button cancelButton;

    @FXML
    public void returnLoginPage() throws IOException {
        new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void subscribe() throws IOException {
        System.out.println("pwd "+pwd.getText());
        System.out.println("repeatPwd "+repeatPwd.getText());
    }

}
