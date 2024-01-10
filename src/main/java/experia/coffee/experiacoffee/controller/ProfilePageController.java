package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)  {

    }

    @FXML
    private AnchorPane profilePageAnchorPane;

    @FXML
    public Button turnToHomeButton;


    @FXML
    public void onTurnToHome() throws IOException {
        new SceneSwitch(profilePageAnchorPane, "prova.fxml");
    }
}
