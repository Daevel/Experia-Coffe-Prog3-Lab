package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.LoginQuery;
import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    public Button loginButton;
    @FXML
    public Button signUpButton;

    @FXML
    public TextField fieldUsername;

    @FXML
    public TextField fieldPassword;


    @FXML
    public void onSwitch2Click() throws IOException {
        new SceneSwitch(scene2AnchorPane, "prova.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        System.out.println("Login page controllore initialized");
    }

    @FXML
    public void openSignUpPage() throws IOException {
        new SceneSwitch(scene2AnchorPane, "signUp.fxml");
    }

    @FXML
    public void openHomePage() throws IOException {
        new SceneSwitch(scene2AnchorPane, "prova.fxml");
    }

    @FXML
    public void onLogin() throws IOException {
        experia.coffee.experiacoffee.model.Utente utente = new experia.coffee.experiacoffee.model.Utente(fieldUsername.getText(), fieldPassword.getText());
        experia.coffee.experiacoffee.data.LoginQuery query = new LoginQuery();
        query.loginUser(utente);

        if(query.loginUser(utente)) {
            openHomePage();
        } else {
            System.out.println("Credenziali errate riprovare");
        }
    }

}
