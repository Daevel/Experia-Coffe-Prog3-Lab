package experia.coffee.experiacoffee.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PopupWindow extends Alert {


    public PopupWindow(AlertType alertType, String message, String title) {
        super(alertType);
        setTitle(title);
        setContentText(message);
        initStyle(StageStyle.UTILITY);
        setHeaderText(null);
        setGraphic(null);


        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/experia/coffee/experiacoffee/assets/ExperiaFavicon.png").toString()));


        // Chiudi automaticamente la finestra dopo 3 secondi
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> close()));
        timeline.play();
    }

    public static void showAlert(AlertType alertType, String message, String title) {
        PopupWindow alert = new PopupWindow(alertType, message, title);
        alert.showAndWait();
    }

}
