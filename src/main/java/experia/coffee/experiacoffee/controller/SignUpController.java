package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.data.CartQuery;
import experia.coffee.experiacoffee.data.SignupQuery;
import experia.coffee.experiacoffee.data.UserQuery;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        this.errorMismatchPassword.setVisible(false);
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

    @FXML
    public TextField fiscalCode;

    @FXML
    public TextField birthDate;

    /* BUTTONS */
    @FXML
    public Button confirmSubscription;

    @FXML
    public Button cancelButton;

    @FXML
    public Label errorMismatchPassword;

    @FXML
    public void returnLoginPage() throws IOException {
        new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void onSignUp() throws IOException {
        if(passwordMismatchCheck(pwd.getText(), repeatPwd.getText())) {
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
                    .setSCADENZA_CARTA(expirationDate.getText())
                    .setCODICE_FISCALE(fiscalCode.getText())
                    .setDATA_DI_NASCITA(birthDate.getText());
            experia.coffee.experiacoffee.data.SignupQuery query = new SignupQuery();

            boolean signUpSuccess = query.signUpUser(utente.build());

            if (signUpSuccess) {
                experia.coffee.experiacoffee.data.UserQuery userQuery = new UserQuery();
                int retrieveUserID = userQuery.getUserID(utente.build());
                    if (retrieveUserID != -1) {
                        createCart(retrieveUserID, email.getText());

                        // mostra messaggio di alert
                        PopupWindow.showAlert(Alert.AlertType.INFORMATION,Constants.SIGNUP_CORRECT, Constants.SIGNUP);

                            try {
                                new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                    } else {
                        System.out.println("retrieveUserID ha riportato un valore di -1");
                    }
            } else {
                PopupWindowError.showErrorAlert(Alert.AlertType.ERROR,Constants.SIGNUP_ERROR, Constants.SIGNUP);
            }
        } else {
            errorMismatchPassword.setVisible(true);
            errorMismatchPassword.setText("Le password devono coincidere");
        }
    }

    private void createCart(int userID, String userEmail) {
        String cartId = "CRT - " + userID;
        experia.coffee.experiacoffee.data.CartQuery query = new CartQuery();
        query.createCart(cartId, userEmail);
    }

    public boolean passwordMismatchCheck(String password, String repeatPassword) {
        return password.equalsIgnoreCase(repeatPassword);
    }

}
