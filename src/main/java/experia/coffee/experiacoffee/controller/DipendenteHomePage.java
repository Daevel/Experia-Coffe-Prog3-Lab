package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.model.*;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.StatePattern.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DipendenteHomePage implements Initializable {

    @FXML
    private AnchorPane DipendenteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton;

    // TABELLA ORDINI
    @FXML
    public TableView<Ordine> orderList = new TableView<>();

    @FXML
    public TableColumn<Ordine, String> colID_ORDINE;

    @FXML
    public TableColumn<Ordine, String> colORDINATO_DA;

    @FXML
    public TableColumn<Ordine, String> colDESTINAZIONE;

    @FXML
    public TableColumn<Ordine, String> colFILIALE_IN_CARICO;

    @FXML
    public TableColumn<Ordine, String> colCORRIERE_IN_CARICO;

    @FXML
    public TableColumn<Ordine, Integer> colNUMERO_ORDINE;

    @FXML
    public TableColumn<Ordine, String> colSTATO_ORDINE;

    // TABELLA MAGAZZINO

    @FXML
    public TableView<Magazzino> wareHouseTable = new TableView<>();

    @FXML
    public TableColumn<Magazzino, String> col_WAREHOUSE_ID_PRODOTTO;

    @FXML
    public TableColumn<Magazzino, String> col_WAREHOUSE_NOME_PRODOTTO;

    @FXML
    public TableColumn<Magazzino, Integer> col_WAREHOUSE_QUANTITA_PRODOTTO;

    @FXML
    public  TableColumn<Magazzino, String> col_WAREHOUSE_NOME_FILIALE;

    @FXML
    public Label welcomeLabel;

    @FXML
    public Button clearFieldsButton, updateOrderButton;

    @FXML
    public TextField updateOrder_EMAIL_CLIENTE, updateOrder_NUMERO_ORDINE, updateOrder_FILIALE_IN_CARICO, updateOrder_CORRIERE_IN_CARICO;

    @FXML
    public ToggleButton makeOrderInTransitButton, makeOrderInQueueButton, makeOrderDeliveredButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        showOrderList();
        showWarehouseList();

        if (utente != null) {
            String fiscalCode = utente.getCODICE_FISCALE();
            welcomeLabel.setText("Autenticato come " + fiscalCode);
        } else {
            System.out.println("L'utente e' null nella Home page");
        }

        colSTATO_ORDINE.setCellFactory(column -> {
            return new TextFieldTableCell<Ordine, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    // Verifica se la riga corrente ha dati
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


    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "loginPage.fxml");
    }

    @FXML
    public void goToProfilePage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "profilePage.fxml");
    }

    @FXML
    public void showOrderList() {
        experia.coffee.experiacoffee.data.OrderQuery query = new experia.coffee.experiacoffee.data.OrderQuery();
        ObservableList<Ordine> list = query.getOrderList();
        colID_ORDINE.setCellValueFactory(new PropertyValueFactory<Ordine, String>("ID_ORDINE"));
        colNUMERO_ORDINE.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("NUMERO_ORDINE"));
        colORDINATO_DA.setCellValueFactory(new PropertyValueFactory<Ordine, String>("ORDINATO_DA"));
        colDESTINAZIONE.setCellValueFactory(new PropertyValueFactory<Ordine, String>("DESTINAZIONE"));
        colFILIALE_IN_CARICO.setCellValueFactory(new PropertyValueFactory<Ordine, String>("FILIALE_IN_CARICO"));
        colCORRIERE_IN_CARICO.setCellValueFactory(new PropertyValueFactory<Ordine, String>("CORRIERE_IN_CARICO"));
        colSTATO_ORDINE.setCellValueFactory(new PropertyValueFactory<Ordine, String>("STATO_ORDINE"));

        orderList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ordine orderSelected = orderList.getSelectionModel().getSelectedItem();
            }
        });

        orderList.setItems(list);
    }

    @FXML
    public void setOrderToComplete() {
        handleToggleButtonAction();
    }

    @FXML
    public void setOrderToInTransit() {
        handleToggleButtonAction();
    }

    @FXML
    public void setOrderToInQueue() {
        handleToggleButtonAction();
    }

    @FXML
    public void showWarehouseList () {
        experia.coffee.experiacoffee.data.WarehouseQuery query = new experia.coffee.experiacoffee.data.WarehouseQuery();
        ObservableList<Magazzino> list = query.getWarehouseList();
        col_WAREHOUSE_NOME_FILIALE.setCellValueFactory(new PropertyValueFactory<Magazzino, String>("NOME_FILIALE"));
        col_WAREHOUSE_ID_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Magazzino, String>("ID_PRODOTTO"));
        col_WAREHOUSE_NOME_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Magazzino, String>("NOME_PRODOTTO"));
        col_WAREHOUSE_QUANTITA_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Magazzino, Integer>("QUANTITA_PRODOTTO"));
        wareHouseTable.setItems(list);
    }

    @FXML
    private void clearFields() {
        this.updateOrder_EMAIL_CLIENTE.setText("");
        this.updateOrder_CORRIERE_IN_CARICO.setText("");
        this.updateOrder_FILIALE_IN_CARICO.setText("");
        this.updateOrder_NUMERO_ORDINE.setText("");
        this.updateOrderButton.setText("null");
    }

    @FXML
    public void updateOrder() throws IOException {
            experia.coffee.experiacoffee.data.OrderQuery query = new OrderQuery();

            int orderNumber = Integer.parseInt(updateOrder_NUMERO_ORDINE.getText());
            String emailUser = updateOrder_EMAIL_CLIENTE.getText();
            String filialeInCarico = updateOrder_FILIALE_IN_CARICO.getText();
            String corriereInCarico = updateOrder_CORRIERE_IN_CARICO.getText();
            String statoOrdine = handleToggleButtonAction();

            OrderState orderState = getStateInstance(statoOrdine);

            boolean updateSuccessfull = query.updateOrder(emailUser, orderNumber, filialeInCarico, corriereInCarico, statoOrdine);
            if (updateSuccessfull) {
                clearFields();
                orderState.applyStateStyle(orderList);
                showOrderList();
            }
    }

    @FXML
    private String handleToggleButtonAction() {
        if(makeOrderDeliveredButton.isSelected()) {
            makeOrderInQueueButton.setSelected(false);
            makeOrderInTransitButton.setSelected(false);
            System.out.println("completed is selected");
            return makeOrderDeliveredButton.getText();
        } else if (makeOrderInTransitButton.isSelected()) {
            makeOrderDeliveredButton.setSelected(false);
            makeOrderInQueueButton.setSelected(false);
            System.out.println("in transit is selected");
            return makeOrderInTransitButton.getText();
        } else if (makeOrderInQueueButton.isSelected()) {
            makeOrderDeliveredButton.setSelected(false);
            makeOrderInTransitButton.setSelected(false);
            System.out.println("In queue is selected");
            return makeOrderInQueueButton.getText();
        }
        return null;
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
