package experia.coffee.experiacoffee.utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupWindowError extends Alert {
    public PopupWindowError(AlertType alertType, String message, String title) {
        super(alertType);
        setTitle(title);
        setContentText(message);
        initStyle(StageStyle.DECORATED);
        setHeaderText(null);
        setGraphic(null);


        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/experia/coffee/experiacoffee/assets/ExperiaFavicon.png").toString()));

    }

    public static void showErrorAlert(AlertType alertType, String message, String title) {
        PopupWindowError errorAlert = new PopupWindowError(alertType, message, title);
        errorAlert.showAndWait();
    }

}
