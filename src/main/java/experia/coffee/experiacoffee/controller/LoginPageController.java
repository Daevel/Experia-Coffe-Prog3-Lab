package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {

    @FXML
    private AnchorPane scene2AnchorPane;

    @FXML
    public Button returnToHomeButton;

    @FXML
    public Button signUpButton;

    @FXML
    public void onSwitch2Click() throws IOException {
        new SceneSwitch(scene2AnchorPane, "prova.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {

    }

    @FXML
    public void openSignUpPage() throws IOException {
        new SceneSwitch(scene2AnchorPane, "signUp.fxml");
    }

}
