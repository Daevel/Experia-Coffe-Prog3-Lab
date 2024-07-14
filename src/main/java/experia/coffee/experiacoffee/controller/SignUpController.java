package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.data.CartQuery;
import experia.coffee.experiacoffee.data.SignupQuery;
import java.time.format.DateTimeFormatter;
import experia.coffee.experiacoffee.data.UserQuery;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import experia.coffee.experiacoffee.validation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    public Hyperlink termsAndConditionsExperiaCoffee;

    @FXML
    public void returnLoginPage() throws IOException {
        new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void onSignUp() throws IOException {

        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String birthDateFormatted = birthDate.getValue() != null ? birthDate.getValue().format(DATE_FORMATTER) : "";
        String expirationDateFormatted = expirationDate.getValue() != null ? expirationDate.getValue().format(DATE_FORMATTER) : "";

        if (PasswordValidation.checkPasswordIntegrity(pwd.getText(), repeatPwd.getText())) {
            if (EmailValidation.patternMatches(email.getText())) {
                if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber.getText())) {
                    if(CAPValidator.isValidCAP(postalCode.getText())) {
                        if (FiscalcodeValidator.isValidCodiceFiscale(fiscalCode.getText())) {
                            if(CardOwnerValidator.isCardOwnerNameValid(name.getText(), surname.getText(), cardOwner.getText())) {
                                if (DateValidator.isValidDate(birthDateFormatted)) {
                                    if (DateValidator.isValidDate(expirationDateFormatted)) {
                                        if (personalDataAgreement.isSelected()) {
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
                                                PopupWindow.showAlert(Alert.AlertType.INFORMATION, Constants.SIGNUP_CORRECT, Constants.SIGNUP);
                                                try {
                                                    new SceneSwitch(signUpAnchorPane, "loginPage.fxml");
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            } else {
                                                PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_ERROR_EMAIL_ALREADY_EXIST, Constants.SIGNUP);
                                            }
                                        } else {
                                            PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_MISSING_ACCEPT_TERMS_AND_POLICY, Constants.SIGNUP);
                                        }
                                    } else {
                                        PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_INVALID_DATE, Constants.SIGNUP);
                                    }
                                } else {
                                    PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_INVALID_DATE, Constants.SIGNUP);
                                }
                            } else {
                                PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_CARD_OWNER_MISMATCH, Constants.SIGNUP);
                            }
                        } else {
                            PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_INVALID_FISCAL_CODE, Constants.SIGNUP);
                        }
                    } else {
                        PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_INVALID_POSTAL_CODE, Constants.SIGNUP);
                    }
                } else {
                    PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.SIGNUP_INVALID_PHONE_NUMBER, Constants.SIGNUP);
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

    @FXML
    private void openTermsAndConditions() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Termini e Condizioni");
        alert.setHeaderText("Termini e Condizioni di Experia Coffee");

        // Aggiungi l'icona alla finestra di dialogo
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/experia/coffee/experiacoffee/assets/ExperiaFavicon.png").toString()));

        // Apply CSS styling
        alert.getDialogPane().getStylesheets().add(this.getClass().getResource("/experia/coffee/experiacoffee/styles/popupStyles.css").toString());
        alert.getDialogPane().getStyleClass().add("term");

        TextArea textArea = new TextArea(getTermsAndConditionsText());
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefSize(400, 300);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    private String getTermsAndConditionsText() {
        return "Termini e Condizioni di Experia Coffee\n\n" +
                "1. Accettazione dei Termini\n" +
                "Utilizzando il nostro sito web e acquistando prodotti dal nostro shop online, accetti i seguenti termini e condizioni. " +
                "Si prega di leggere attentamente questi termini prima di effettuare qualsiasi acquisto.\n\n" +
                "2. Prodotti\n" +
                "Tutti i prodotti per il caffè venduti sul nostro sito sono descritti in modo accurato. Tuttavia, non garantiamo che le descrizioni " +
                "dei prodotti siano complete, aggiornate o prive di errori. Le immagini dei prodotti sono solo a scopo illustrativo.\n\n" +
                "3. Prezzi\n" +
                "I prezzi dei prodotti sono indicati sul nostro sito web e possono essere soggetti a modifiche senza preavviso. " +
                "I prezzi includono l'IVA, ma non includono le spese di spedizione, che saranno calcolate al momento del checkout.\n\n" +
                "4. Pagamenti\n" +
                "Accettiamo diversi metodi di pagamento, tra cui carte di credito e debito, PayPal e altri metodi specificati sul nostro sito. " +
                "Il pagamento deve essere effettuato al momento dell'ordine.\n\n" +
                "5. Spedizione\n" +
                "Effettuiamo spedizioni in tutto il territorio nazionale. I tempi di consegna variano a seconda della località di destinazione. " +
                "Non siamo responsabili per eventuali ritardi causati da eventi al di fuori del nostro controllo.\n\n" +
                "6. Resi e Rimborsi\n" +
                "Se non sei soddisfatto del tuo acquisto, puoi restituire i prodotti entro 14 giorni dalla data di ricezione per un rimborso completo. " +
                "I prodotti devono essere restituiti nelle loro condizioni originali. Le spese di spedizione per i resi sono a carico del cliente.\n\n" +
                "7. Privacy\n" +
                "Il trattamento dei tuoi dati personali è regolato dalla nostra Informativa sulla Privacy, disponibile sul nostro sito web.\n\n" +
                "8. Modifiche ai Termini\n" +
                "Ci riserviamo il diritto di modificare questi termini e condizioni in qualsiasi momento. Le modifiche saranno pubblicate sul nostro sito web. " +
                "Si consiglia di controllare periodicamente questa pagina per eventuali aggiornamenti.\n\n" +
                "9. Contatti\n" +
                "Per qualsiasi domanda o chiarimento sui nostri termini e condizioni, contattaci all'indirizzo email support@experiacoffee.com.\n\n" +
                "Grazie per aver scelto Experia Coffee per i tuoi acquisti di prodotti per il caffè!";
    }

}
