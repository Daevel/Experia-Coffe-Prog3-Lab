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

public class DipendenteHomePage implements Initializable {

    @FXML
    private AnchorPane DipendenteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton;

    @FXML
    public Label welcomeLabel;

    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "loginPage.fxml");
    }

    @FXML
    public void goToProfilePage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "profilePage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        if (utente != null) {
            String fiscalCode = utente.getCODICE_FISCALE();

            welcomeLabel.setText("Autenticato come " + fiscalCode);

        } else {
            System.out.println("L'utente e' null nella Home page");
        }
    }
}
