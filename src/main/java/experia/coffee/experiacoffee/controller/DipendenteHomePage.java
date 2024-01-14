package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DipendenteHomePage implements Initializable {

    @FXML
    private AnchorPane DipendenteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton;

    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "loginPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
