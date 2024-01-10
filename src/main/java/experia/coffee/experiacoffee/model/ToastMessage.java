package experia.coffee.experiacoffee.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ToastMessage {

    public void showToast(String message) {
        Stage toastStage = new Stage();
        toastStage.initStyle(StageStyle.UNDECORATED);
        toastStage.setAlwaysOnTop(true);

        Label label = new Label(message);
        label.getStyleClass().add("toast-message");

        StackPane root = new StackPane(label);
        root.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/experia/coffee/experiacoffee/styles/style.css").toExternalForm());

        toastStage.setScene(scene);

        Timeline timeline = new Timeline();
        KeyFrame fadeIn = new KeyFrame(Duration.millis(200), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1));
        KeyFrame fadeOut = new KeyFrame(Duration.millis(2000), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0));
        timeline.getKeyFrames().addAll(fadeIn, fadeOut);
        timeline.setOnFinished(event -> toastStage.close());

        toastStage.show();
        timeline.play();
    }

}
