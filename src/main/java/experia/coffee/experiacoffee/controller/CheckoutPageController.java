package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.data.UserQuery;
import experia.coffee.experiacoffee.model.*;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.SingletonPattern.ValoreTotaleSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CheckoutPageController implements Initializable {

    @FXML
    private AnchorPane checkoutPane;

    @FXML
    public Button returnToHomePageButton, proceedOrder;

    @FXML
    public Label subTotalProducts,
            travelFees,
            retrieveFees,
            ivaPercentage,
            totalProducts,
            checkoutAddress,
            checkoutNumberAddress,
            checkoutMobilePhone,
            checkoutCreditNumber,
            checkoutExpirationDateCard;

    private Utente singletonUser;

    @FXML
    public void onReturnHomePage() throws IOException {
        new SceneSwitch(checkoutPane, "clienteHomePage.fxml");
    }

    @FXML
    public void onSubmitOrder() throws IOException {
        String email = singletonUser.getEMAIL();
        String cartID = retrieveCartID(email);
        createOrder(cartID, email);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        float valoreTotale = ValoreTotaleSingleton.getInstance().getValoreTotale();
        
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");

        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();


        if (utente != null) {

            singletonUser = utente;

            String via = utente.getVIA();
            String num_carta = utente.getNUM_CARTA();
            String cellulare = utente.getCELLULARE();
            String n_civico = utente.getN_CIVICO();
            String scadenzaCarta = utente.getSCADENZA_CARTA();

            checkoutAddress.setText("Indirizzo: " + via);
            checkoutCreditNumber.setText("Carta di credito: " + num_carta);
            checkoutMobilePhone.setText("Cellulare: " + cellulare);
            checkoutNumberAddress.setText("Civico: " + n_civico);
            checkoutExpirationDateCard.setText("Scadenza carta: " + scadenzaCarta);
        } else {
            System.out.println("Utente null");
        }
        travelFees.setText("Costi di trasposto: FREE");
        retrieveFees.setText("Costi di spedizione: FREE");
        subTotalProducts.setText("Prodotto: €" + decimalFormat.format(valoreTotale));
        ivaPercentage.setText("IVA(20%): €" + decimalFormat.format(calculateIVA(valoreTotale)));
        totalProducts.setText("Totale: €"+ decimalFormat.format(valoreTotale+calculateIVA(valoreTotale)));
    }

    private float calculateIVA(float subTotale) {
       final int IVA_PERCENTAGE = 20;
       return (subTotale * IVA_PERCENTAGE)/(100);
    }

    private void createOrder(String userID, String userEmail) throws IOException {
        String cartId = "ORD - 00" + userID;
        experia.coffee.experiacoffee.data.OrderQuery query = new OrderQuery();
        boolean isOrderSuccessful = query.createOrder(cartId, userEmail);
        if (isOrderSuccessful) {
            new SceneSwitch(checkoutPane, "thanksPageAfterOrder.fxml");
        }
        System.out.println("Errore nella creazione dell'ordine");
    }

    private String retrieveCartID(String userEmail) {
        experia.coffee.experiacoffee.data.UserQuery userQuery = new UserQuery();
        return userQuery.retrieveCartID(userEmail);
    }

}
