package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.LoginQuery;
import experia.coffee.experiacoffee.model.LoginResult;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.Utente;
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
    public Button loginButton;
    @FXML
    public Button signUpButton;

    @FXML
    public TextField fieldUsername;

    @FXML
    public TextField fieldPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {}

    @FXML
    public void openSignUpPage() throws IOException {
        new SceneSwitch(scene2AnchorPane, "signUp.fxml");
    }

    @FXML
    public void onLogin() throws IOException {
        Utente.UtenteBuilder utente = new Utente.UtenteBuilder(fieldUsername.getText(), fieldPassword.getText());
        experia.coffee.experiacoffee.data.LoginQuery query = new LoginQuery();

        LoginResult loginResult = query.loginUser(utente.build());

        if(loginResult.isSuccess()) {

            String ruoloUtente = loginResult.getRuolo();

            if ("cliente".equals(ruoloUtente)) {
                new SceneSwitch(scene2AnchorPane, "clienteHomePage.fxml");
            } else if ("dipendente".equals(ruoloUtente)){
                new SceneSwitch(scene2AnchorPane, "dipendenteHomePage.fxml");
            }
        } else {
            System.out.println("Credenziali errate riprovare");
        }
    }

}
