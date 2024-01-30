package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.LoginQuery;
import experia.coffee.experiacoffee.model.LoginResult;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.Utente;
import experia.coffee.experiacoffee.model.UtenteSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    public Label errorInvalidCredentials;

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

        Utente userLogged = query.loginUser(utente.build());

        if(userLogged != null) {

            String ruoloUtente = userLogged.getRUOLO();
            String nomeUtente = userLogged.getNAME();
            String cognomeUtente = userLogged.getSURNAME();
            String codiceFiscale = userLogged.getCODICE_FISCALE();
            String dataDiNascita = userLogged.getDATA_DI_NASCITA();
            String via = userLogged.getVIA();
            String num_carta = userLogged.getNUM_CARTA();
            String cellulare = userLogged.getCELLULARE();
            String n_civico = userLogged.getN_CIVICO();
            String scadenzaCarta = userLogged.getSCADENZA_CARTA();

            UtenteSingleton utenteSingleton = UtenteSingleton.getInstance();
            utenteSingleton.setUtente(utente
                    .setRUOLO(ruoloUtente)
                    .setNAME(nomeUtente)
                    .setSURNAME(cognomeUtente)
                    .setCODICE_FISCALE(codiceFiscale)
                    .setDATA_DI_NASCITA(dataDiNascita)
                    .setVIA(via)
                    .setNUM_CARTA(num_carta)
                    .setCELLULARE(cellulare)
                    .setN_CIVICO(n_civico)
                    .setSCADENZA_CARTA(scadenzaCarta)
                    .build());

            if ("cliente".equals(ruoloUtente)) {
                new SceneSwitch(scene2AnchorPane, "clienteHomePage.fxml");
            } else if ("dipendente".equals(ruoloUtente)){
                new SceneSwitch(scene2AnchorPane, "dipendenteHomePage.fxml");
            }
        } else {
            errorInvalidCredentials.setText("Credenziali errate o non valide, riprovare.");
            System.out.println("Credenziali errate riprovare");
        }
    }

}
