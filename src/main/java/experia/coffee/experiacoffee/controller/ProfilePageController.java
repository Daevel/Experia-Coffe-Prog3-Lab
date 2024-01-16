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

public class ProfilePageController implements Initializable {

    @FXML
    private AnchorPane profilePageAnchorPane;

    @FXML
    public Button turnToHomeButton;

    @FXML
    public Button profileLogoutButton;

    @FXML
    public Label profileName;
    @FXML
    public Label profileSurname;
    @FXML
    public Label profileEmail;
    @FXML
    public Label profileFiscalCode;
    @FXML
    public Label profileBirthDate;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        if (utente != null) {
            String nome = utente.getNAME();
            String cognome = utente.getSURNAME();
            String email = utente.getEMAIL();
            String fiscalCode = utente.getCODICE_FISCALE();
            String birthDate = utente.getDATA_DI_NASCITA();

            profileName.setText("Nome: " + nome);
            profileSurname.setText("Cognome: " + cognome);
            profileEmail.setText("Email: " + email);
            profileFiscalCode.setText("Codice fiscale: " + fiscalCode);
            profileBirthDate.setText("Data di nascita: " + birthDate);


        } else {
            System.out.println("L'utente e' null nella Home page");
        }
    }




    @FXML
    public void onTurnToHome() throws IOException {
        new SceneSwitch(profilePageAnchorPane, "prova.fxml");
    }

    @FXML
    public void onLogout() throws IOException {
        new SceneSwitch(profilePageAnchorPane, "loginPage.fxml");
    }
}
