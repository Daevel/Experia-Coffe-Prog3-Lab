package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.Utente;
import experia.coffee.experiacoffee.model.UtenteSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteHomePage implements Initializable {

    @FXML
    private AnchorPane ClienteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton;

    @FXML
    public Button profilePageButton;

    @FXML
    public Label welcomeLabel;

    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(ClienteHomePageAnchor, "loginPage.fxml");
    }

    @FXML
    public void goToProfilePage() throws  IOException {
        new SceneSwitch(ClienteHomePageAnchor, "profilePage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        if (utente != null) {

            String nome = utente.getNAME();
            String cognome = utente.getSURNAME();

            welcomeLabel.setText("Benvenuto, " + nome + " " + cognome + "!");

        } else {
            System.out.println("L'utente e' null nella Home page");
        }



    }
}
