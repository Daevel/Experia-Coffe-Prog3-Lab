package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        System.out.println("subscription works");
    }

}
