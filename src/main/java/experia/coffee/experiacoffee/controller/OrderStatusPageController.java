package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderStatusPageController implements Initializable {

    @FXML
    public AnchorPane orderStatusAnchorPane;
    @FXML
    public Button clienteHomePageButton, logoutButton;

    @FXML
    public void onLogout() throws IOException {
        new SceneSwitch(orderStatusAnchorPane, "loginPage.fxml");
    }

    @FXML
    public void onReturnHomePage() throws IOException {
        new SceneSwitch(orderStatusAnchorPane, "clienteHomePage.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
