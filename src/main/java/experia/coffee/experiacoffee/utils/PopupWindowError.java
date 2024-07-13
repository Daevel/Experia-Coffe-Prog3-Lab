package experia.coffee.experiacoffee.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.logging.Logger;

public class PopupWindowError extends Alert {
    public PopupWindowError(AlertType alertType, String message, String title) {
        super(alertType);
        setTitle(title);
        setContentText(message);
        initStyle(StageStyle.DECORATED);
        setHeaderText("Si è verificato un errore");
        setGraphic(null);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/experia/coffee/experiacoffee/assets/ExperiaFavicon.png").toString()));

        // Apply CSS styling
        getDialogPane().getStylesheets().add(this.getClass().getResource("/experia/coffee/experiacoffee/styles/popupStyles.css").toString());

        // Apply specific style class
        getDialogPane().getStyleClass().add(getStyleClassForAlertType(alertType));
    }

    // metodo per scegliere la classe css corretta in base al popup evocato
    private String getStyleClassForAlertType(AlertType alertType) {
        switch (alertType) {
            case ERROR:
                return "error";
            case INFORMATION:
                return "info";
            case CONFIRMATION:
                return "confirmation";
            default:
                return "";
        }
    }

    public static void showErrorAlert(AlertType alertType, String message, String title) {
        PopupWindowError errorAlert = new PopupWindowError(alertType, message, title);
        errorAlert.showAndWait();
    }

    // metodo usato per gestire gli errori
    public static void handleException(Exception e) {
        if (e instanceof NullPointerException) {
            showErrorAlert(AlertType.ERROR, "Si è verificato un errore: uno degli attributi è null.", "Errore NullPointerException");
        } else if (e instanceof IllegalArgumentException) {
            showErrorAlert(AlertType.ERROR, "Si è verificato un errore: argomento non valido.", "Errore IllegalArgumentException");
        } else if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            showErrorAlert(AlertType.ERROR, "Errore SQL: " + sqlException.getMessage() + "\nCodice SQL: " + sqlException.getErrorCode() + "\nStato SQL: " + sqlException.getSQLState(), "Errore SQLException");
        } else {
            showErrorAlert(AlertType.ERROR, "Si è verificato un errore inaspettato: " + e.getMessage(), "Errore Generico");
        }
    }

}
