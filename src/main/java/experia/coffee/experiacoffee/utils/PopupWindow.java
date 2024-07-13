package experia.coffee.experiacoffee.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PopupWindow extends Alert {


    public PopupWindow(AlertType alertType, String message, String title) {
        super(alertType);
        setTitle(title);
        setContentText(message);
        initStyle(StageStyle.DECORATED);
        setHeaderText("Messaggio di sistema");
        setGraphic(null);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/experia/coffee/experiacoffee/assets/ExperiaFavicon.png").toString()));

        // Apply CSS styling
        getDialogPane().getStylesheets().add(this.getClass().getResource("/experia/coffee/experiacoffee/styles/popupStyles.css").toString());

        // Apply specific style class
        getDialogPane().getStyleClass().add(getStyleClassForAlertType(alertType));

        // Chiudi automaticamente la finestra dopo 3 secondi
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> close()));
        timeline.play();
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

    public static void showAlert(AlertType alertType, String message, String title) {
        PopupWindow alert = new PopupWindow(alertType, message, title);
        alert.showAndWait();
    }

}
