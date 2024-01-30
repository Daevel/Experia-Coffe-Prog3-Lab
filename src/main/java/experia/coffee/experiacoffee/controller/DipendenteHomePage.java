package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.OrderQuery;
import experia.coffee.experiacoffee.data.WarehouseQuery;
import experia.coffee.experiacoffee.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    }

    @FXML
    private void clearFields() {
        this.updateOrder_EMAIL_CLIENTE.setText("");
        this.updateOrder_CORRIERE_IN_CARICO.setText("");
        this.updateOrder_FILIALE_IN_CARICO.setText("");
        this.updateOrder_NUMERO_ORDINE.setText("");
    }

    @FXML
    public void updateOrder() throws IOException {
        try {
            experia.coffee.experiacoffee.data.OrderQuery query = new OrderQuery();
            int orderNumber = Integer.parseInt(updateOrder_NUMERO_ORDINE.getText());
            boolean updateSuccessfull = query.updateOrder(updateOrder_EMAIL_CLIENTE.getText(), orderNumber ,updateOrder_FILIALE_IN_CARICO.getText(), updateOrder_CORRIERE_IN_CARICO.getText());
            if (updateSuccessfull) {
                System.out.println("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
