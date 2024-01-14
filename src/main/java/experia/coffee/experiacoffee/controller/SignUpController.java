package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.SignupQuery;
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

    @FXML
    public TextField cardNumber;

    @FXML
    public TextField cvvNumber;

    @FXML
    public TextField expirationDate;

    @FXML
    public TextField cardOwner;

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
    public void onSignUp() throws IOException {
        Utente.UtenteBuilder utente = new Utente.UtenteBuilder(email.getText(), pwd.getText())
                .setNAME(name.getText())
                .setSURNAME(surname.getText())
                .setCELLULARE(phoneNumber.getText())
                .setVIA(streetAddress.getText())
                .setN_CIVICO(streetNumber.getText())
                .setCITTA(city.getText())
                .setCAP(postalCode.getText())
                .setNUM_CARTA(cardNumber.getText())
                .setCVV_CARTA(cvvNumber.getText())
                .setINTESTATARIO_CARTA(cardOwner.getText())
                .setSCADENZA_CARTA(expirationDate.getText());
        experia.coffee.experiacoffee.data.SignupQuery query = new SignupQuery();

        boolean signUpSuccess = query.signUpUser(utente.build());

        if (signUpSuccess) {
            new SceneSwitch(signUpAnchorPane, "prova.fxml");
        } else {
            System.out.println("Registrazione fallita, riprovare");
        }
    }

}
