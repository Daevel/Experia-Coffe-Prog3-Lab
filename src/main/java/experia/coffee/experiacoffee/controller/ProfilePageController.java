package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.UserQuery;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
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

    // CHANGE EMAIL PROCEDURE
    @FXML
    public Button changeEmailButton, changeEmail_ConfirmButton, changeEmail_CloseButton;
    @FXML
    public Separator changeEmail_Separator;
    @FXML
    public TextField changeEmail_TextField;
    @FXML
    public Label changeEmail_Label;

    // RESET PASSWORD PROCEDURE
    @FXML
    public Button resetPasswordButton, resetPassword_ConfirmButton, resetPassword_CloseButton;
    @FXML
    public Separator resetPassword_Separator;
    @FXML
    public TextField resetPassword_TextField;
    @FXML
    public TextField resetPassword_repeatPassword_TextField;

    @FXML
    public Label resetPassword_repeatPassword_Label;
    @FXML
    public Label resetPassword_Label;

    @FXML
    public Label passwordMismatchText;

    // ALTRO

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

    private String userRole;
    private String userFiscalCode;
    private String userEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        changeEmail_TextField.setVisible(false);
        changeEmail_Label.setVisible(false);
        changeEmail_Separator.setVisible(false);
        changeEmail_ConfirmButton.setVisible(false);
        changeEmail_CloseButton.setVisible(false);

        resetPassword_TextField.setVisible(false);
        resetPassword_Label.setVisible(false);
        resetPassword_Separator.setVisible(false);
        resetPassword_ConfirmButton.setVisible(false);
        resetPassword_CloseButton.setVisible(false);
        resetPassword_repeatPassword_TextField.setVisible(false);
        resetPassword_repeatPassword_Label.setVisible(false);

        passwordMismatchText.setVisible(true);

        if (utente != null) {
            String nome = utente.getNAME();
            String cognome = utente.getSURNAME();
            String email = utente.getEMAIL();
            String fiscalCode = utente.getCODICE_FISCALE();
            String birthDate = utente.getDATA_DI_NASCITA();
            String role = utente.getRUOLO();

            userRole = role;
            userFiscalCode = fiscalCode;
            userEmail = email;

            if (role.equalsIgnoreCase("dipendente")) {
                // i dipendenti non devono cambiare la mail, ma solo la password
                changeEmailButton.setVisible(false);
            }

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
        if (userRole.equalsIgnoreCase("cliente")) {
            new SceneSwitch(profilePageAnchorPane, "clienteHomePage.fxml");
        } else if (userRole.equalsIgnoreCase("dipendente")) {
            new SceneSwitch(profilePageAnchorPane, "dipendenteHomePage.fxml");
        } else {
            new SceneSwitch(profilePageAnchorPane, ".fxml");
        }
    }

    @FXML
    public void onLogout() throws IOException {
        new SceneSwitch(profilePageAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void openResetPasswordPopup() {
        resetPassword_TextField.setVisible(true);
        resetPassword_Label.setVisible(true);
        resetPassword_Separator.setVisible(true);
        resetPassword_ConfirmButton.setVisible(true);
        resetPassword_repeatPassword_TextField.setVisible(true);
        resetPassword_CloseButton.setVisible(true);
        resetPassword_repeatPassword_Label.setVisible(true);
    }

    @FXML
    public void openChangeEmailPopup() {
        changeEmail_TextField.setVisible(true);
        changeEmail_Label.setVisible(true);
        changeEmail_Separator.setVisible(true);
        changeEmail_ConfirmButton.setVisible(true);
        changeEmail_CloseButton.setVisible(true);
    }

    @FXML
    public void closeDialogEmail() {
        changeEmail_TextField.setVisible(false);
        changeEmail_Label.setVisible(false);
        changeEmail_Separator.setVisible(false);
        changeEmail_ConfirmButton.setVisible(false);
        changeEmail_CloseButton.setVisible(false);
    }
    @FXML
    public void closeDialogPassword() {
        resetPassword_TextField.setVisible(false);
        resetPassword_Label.setVisible(false);
        resetPassword_Separator.setVisible(false);
        resetPassword_ConfirmButton.setVisible(false);
        resetPassword_CloseButton.setVisible(false);
        resetPassword_repeatPassword_TextField.setVisible(false);
        resetPassword_repeatPassword_Label.setVisible(false);
    }

    @FXML
    public void onChangeEmail() throws IOException {
       String newEmail = changeEmail_TextField.getText();
       String oldEmail = userEmail;

        experia.coffee.experiacoffee.data.UserQuery query = new experia.coffee.experiacoffee.data.UserQuery();
        if(query.changeEmail(newEmail, oldEmail)) {
            closeDialogEmail();
            new SceneSwitch(profilePageAnchorPane, "loginPage.fxml");
        } else {
            System.out.println("Errore nel cambio email");
        }

    }

    @FXML
    public void onResetPassword() throws IOException {
        String email = userEmail;
        String password = resetPassword_TextField.getText();
        String repeatPassword = resetPassword_repeatPassword_TextField.getText();
        String role = userRole;

        experia.coffee.experiacoffee.data.UserQuery query = new UserQuery();

        if (passwordMismatchCheck(password, repeatPassword)) {
            if(query.resetPassword(email, password, role)) {
                closeDialogPassword();
                new SceneSwitch(profilePageAnchorPane, "loginPage.fxml");
            } else {
                System.out.println("Errore nel cambio password");
            }
        } else {
            passwordMismatchText.setText("Le password non coincidono, riprovare.");
        }
    }




    public boolean passwordMismatchCheck(String password, String repeatPassword) {
        return password.equalsIgnoreCase(repeatPassword);
    }
}
