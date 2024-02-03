package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
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

    public String emailUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        try {
            if (utente != null) {
                String email = utente.getEMAIL();
                emailUser = email;
                showOrder();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showOrder() {
        experia.coffee.experiacoffee.data.OrderQuery query = new OrderQuery();
        //query.getOrderByClient(emailUser);
    }

}
