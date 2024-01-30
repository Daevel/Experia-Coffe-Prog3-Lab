package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThanksPageAfterOrderController implements Initializable {

    @FXML
    public AnchorPane thanksPageAfterOrderAnchor;

    @FXML
    public Button returnToHomepageButton, showOpenPageButton;


    @FXML
    public void returnToHomePage () throws IOException {
        new SceneSwitch(thanksPageAfterOrderAnchor, "clienteHomePage.fxml");
    }

    @FXML
    public void openOrderPage () throws IOException {
        new SceneSwitch(thanksPageAfterOrderAnchor, "orderPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
