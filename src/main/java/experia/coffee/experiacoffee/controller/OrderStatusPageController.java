package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.Ordine;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.SingletonPattern.ValoreTotaleSingleton;
import experia.coffee.experiacoffee.model.StatePattern.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class OrderStatusPageController implements Initializable {
    @FXML
    public AnchorPane orderStatusAnchorPane;
    @FXML
    public Button clienteHomePageButton, logoutButton;

    @FXML
    public Label totalePagatoLabel;

    @FXML
    public TableView<Ordine> orderList = new TableView<>();

    @FXML
    public TableColumn<Ordine, String> statusOrder_ID;

    @FXML
    public TableColumn<Ordine, String> statusOrder_ORDINATO_DA;

    @FXML
    public TableColumn<Ordine, String> statusOrder_DESTINAZIONE;

    @FXML
    public TableColumn<Ordine, String> statusOrder_STATO_ORDINE;


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

        ValoreTotaleSingleton valoreTotaleSingleton = ValoreTotaleSingleton.getInstance();
        float valoreTotale = valoreTotaleSingleton.getValoreTotale();
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");

        try {
            if (utente != null) {
                String email = utente.getEMAIL();
                emailUser = email;
                showOrder();
                totalePagatoLabel.setText("Totale pagato: €" + decimalFormat.format(valoreTotale));
            } else {
                totalePagatoLabel.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        statusOrder_STATO_ORDINE.setCellFactory(column -> {
            return new TextFieldTableCell<Ordine, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (!isEmpty() && getItem() != null) {
                        OrderState orderState = getStateInstance(getItem());
                        orderState.applyStateStyle(this);
                    } else {
                        setStyle("");
                    }
                }
            };
        });


    }

    public void showOrder() {
        experia.coffee.experiacoffee.data.OrderQuery query = new experia.coffee.experiacoffee.data.OrderQuery();
        ObservableList<Ordine> list = query.getOrderListByClient(emailUser);

        statusOrder_ID.setCellValueFactory(new PropertyValueFactory<Ordine, String>("ID_ORDINE"));
        statusOrder_ORDINATO_DA.setCellValueFactory(new PropertyValueFactory<Ordine, String>("ORDINATO_DA"));
        statusOrder_DESTINAZIONE.setCellValueFactory(new PropertyValueFactory<Ordine, String>("DESTINAZIONE"));
        statusOrder_STATO_ORDINE.setCellValueFactory(new PropertyValueFactory<Ordine, String>("STATO_ORDINE"));

        orderList.setItems(list);
    }

    private OrderState getStateInstance(String statoOrdine) {
        switch (statoOrdine) {
            case "Consegnato":
                return new DeliveredState();
            case "In Transito":
                return new InTransitState();
            case "In Attesa":
                return new PendingState();
            default:
                return new DefaultState();
        }
    }

}
