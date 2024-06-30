package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.data.CartQuery;
import experia.coffee.experiacoffee.data.SignupQuery;
import experia.coffee.experiacoffee.data.UserQuery;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import experia.coffee.experiacoffee.validation.EmailValidation;
import experia.coffee.experiacoffee.validation.PasswordValidation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public DatePicker expirationDate, birthDate;

    @FXML
    public TextField cardOwner;

    @FXML
    public TextField fiscalCode;

    /* BUTTONS */
    @FXML
    public Button confirmSubscription;

    @FXML
    public Button cancelButton;

    /* CHECKBOX TRATTAMENTO DATI PERSONALI */
    @FXML
    public CheckBox personalDataAgreement;

    @FXML
    public Label errorMismatchPassword;

    @FXML
    public void returnLoginPage() throws IOException {
        new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void onSignUp() throws IOException {
        if(PasswordValidation.checkPasswordIntegrity(pwd.getText(), repeatPwd.getText())) {
            if(EmailValidation.patternMatches(email.getText())) {
                if(personalDataAgreement.isSelected()) {
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
                            .setSCADENZA_CARTA(expirationDate.getValue().toString())
                            .setCODICE_FISCALE(fiscalCode.getText())
                            .setDATA_DI_NASCITA(birthDate.getValue().toString());
                    experia.coffee.experiacoffee.data.SignupQuery query = new SignupQuery();

                    boolean signUpSuccess = query.signUpUser(utente.build());

                    if (signUpSuccess) {
                        experia.coffee.experiacoffee.data.UserQuery userQuery = new UserQuery();
                        createCart(email.getText());
                        PopupWindow.showAlert(Alert.AlertType.INFORMATION,Constants.SIGNUP_CORRECT, Constants.SIGNUP);
                        try {
                            new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        PopupWindowError.showErrorAlert(Alert.AlertType.ERROR,Constants.SIGNUP_ERROR_EMAIL_ALREADY_EXIST, Constants.SIGNUP);
                    }
                } else {
                    PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_MISSING_ACCEPT_TERMS_AND_POLICY, Constants.SIGNUP);
                }
            } else {
                PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_ERROR_EMAIL_PATTERN_INVALID, Constants.SIGNUP);
            }
        } else {
            PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_ERROR_EMAIL_PASSWORD_MISMATCH, Constants.SIGNUP);
            errorMismatchPassword.setVisible(true);
            errorMismatchPassword.setText("Le password devono coincidere");
        }
    }

    private void createCart(String userEmail) {
        experia.coffee.experiacoffee.data.CartQuery query = new CartQuery();
        query.createCart(userEmail);
    }

}
