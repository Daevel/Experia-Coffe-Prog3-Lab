package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.model.*;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.StatePattern.OrderStatus.*;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import experia.coffee.experiacoffee.utils.StatusImpl;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class DipendenteHomePage implements Initializable {

    @FXML
    private AnchorPane DipendenteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton, ticketingPageButton, profilePageButton;
    @FXML
    public ImageView favicon;
    // TABELLA ORDINI
    @FXML
    public TableView<Ordine> orderList = new TableView<>();

    @FXML
    public TableColumn<Ordine, Integer> colID_ORDINE;

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
    public Label ordineEvaso, orderStatusSelected;

    @FXML
    public Button clearFieldsButton, updateOrderButton;

    @FXML
    public TextField updateOrder_ID_ORDINE, updateOrder_NUMERO_ORDINE, updateOrder_FILIALE_IN_CARICO, updateOrder_CORRIERE_IN_CARICO;

    @FXML
    public ToggleButton makeOrderInTransitButton, makeOrderInQueueButton, makeOrderDeliveredButton;

    public String statusSelected;

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
                        StatusImpl impl = new StatusImpl();
                        OrderState orderState = impl.getOrderStateInstance(getItem());
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
        PopupWindow.showAlert(Alert.AlertType.INFORMATION, Constants.LOGOUT_SUCCESS, Constants.LOGOUT);
        new SceneSwitch(DipendenteHomePageAnchor, "loginPage.fxml");
    }

    @FXML
    public void goToProfilePage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "profilePage.fxml");
    }

    @FXML
    public void goToTicketingPage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "dipendenteTicketingPage.fxml");
    }

    @FXML
    public void goToHomePage() throws IOException {
        new SceneSwitch(DipendenteHomePageAnchor, "dipendenteHomePage.fxml");
    }

    @FXML
    public void showOrderList() {
        experia.coffee.experiacoffee.data.OrderQuery query = new experia.coffee.experiacoffee.data.OrderQuery();
        ObservableList<Ordine> list = query.getOrderList();
        colID_ORDINE.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("ID"));
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
        this.updateOrder_ID_ORDINE.setText("");
        this.updateOrder_CORRIERE_IN_CARICO.setText("");
        this.updateOrder_FILIALE_IN_CARICO.setText("");
        this.updateOrder_NUMERO_ORDINE.setText("");
    }

    @FXML
    public void updateOrder() throws IOException {
            experia.coffee.experiacoffee.data.OrderQuery query = new OrderQuery();

            int orderNumber = Integer.parseInt(updateOrder_NUMERO_ORDINE.getText());
            int idOrdine = Integer.parseInt(updateOrder_ID_ORDINE.getText());
            String filialeInCarico = updateOrder_FILIALE_IN_CARICO.getText();
            String corriereInCarico = updateOrder_CORRIERE_IN_CARICO.getText();
            String statoOrdine = statusSelected;

            StatusImpl impl = new StatusImpl();
            OrderState orderState = impl.getOrderStateInstance(statoOrdine);

            boolean updateSuccessfull = query.updateOrder(idOrdine, orderNumber, filialeInCarico, corriereInCarico, statoOrdine);
            if (updateSuccessfull) {
                clearFields();
                orderState.applyStateStyle(orderList);
                PopupWindow.showAlert(Alert.AlertType.INFORMATION, Constants.ORDER_UPDATED_SUCCESS, Constants.ORDER);
                showOrderList();

                if ("Consegnato".equalsIgnoreCase(statoOrdine)) {
                    // Se lo stato e' consegnato cancella l'ordine dopo 3 secondi

                    ordineEvaso.setText("Ordine evaso correttamente");

                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            boolean deleteSuccess = query.deleteOrder(idOrdine);
                            if (deleteSuccess) {
                                ordineEvaso.setVisible(true);
                                showOrderList();

                                TimerTask hideOrderEvaso = new TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(() -> {
                                            ordineEvaso.setVisible(false);
                                        });
                                    };
                                };
                                timer.schedule(hideOrderEvaso, 2000);

                            } else {
                                System.out.println("Errore durante l'eliminazione dell'ordine");
                            }
                        }
                    }, 3000);


                }
            } else {
                PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.ORDER_UPDATED_ERROR, Constants.ORDER);
            }
    }

    @FXML
    private String handleToggleButtonAction() {
        if(makeOrderDeliveredButton.isSelected()) {
            orderStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.ORDER_STATUS_ON_DELIVERED);
            statusSelected = Constants.ORDER_STATUS_ON_DELIVERED;
        } else if (makeOrderInTransitButton.isSelected()) {
            orderStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.ORDER_STATUS_ON_THE_WAY);
            statusSelected = Constants.ORDER_STATUS_ON_THE_WAY;
        } else if (makeOrderInQueueButton.isSelected()) {
            orderStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.ORDER_STATUS_ON_WAITING);
            statusSelected = Constants.ORDER_STATUS_ON_WAITING;
        } else {
            orderStatusSelected.setText(Constants.STATUS_SELECTED + ": ");
        }
        return null;
    }

}
