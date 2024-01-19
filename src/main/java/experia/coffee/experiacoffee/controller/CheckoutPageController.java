package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutPageController implements Initializable {

    @FXML
    private AnchorPane checkoutPane;

    @FXML
    public Button returnToHomePageButton, proceedOrder;

    @FXML
    public Label subTotalProducts,travelFees,retrieveFees,ivaPercentage,totalProducts;


    @FXML
    public void onReturnHomePage() throws IOException {
        new SceneSwitch(checkoutPane, "clienteHomePage.fxml");
    }

    @FXML
    public void onSubmitOrder() {
        System.out.println("Ordine inviato");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
