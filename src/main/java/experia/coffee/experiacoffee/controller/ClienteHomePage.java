package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteHomePage implements Initializable {

    @FXML
    private AnchorPane ClienteHomePageAnchor;
    @FXML
    public Button returnToLoginPageButton;

    @FXML
    public Button profilePageButton, checkoutPageButton;

    @FXML
    public Label welcomeLabel;

    @FXML
    public TableView<Prodotto> productView = new TableView<>();

    @FXML
    public TableColumn<Prodotto, String> colNOME_PRODOTTO, col_PROVENIENZA, col_ID_PRODOTTO, col_ID_FORNITURA;

    @FXML
    public TableColumn<Prodotto, Float> col_PREZZO_PRODOTTO;

    @FXML
    public TableColumn<Prodotto, Integer> col_QUANTITA;

    @FXML
    public TableView<Prodotto> cartView = new TableView<>();

    @FXML
    public TableColumn<Prodotto, String> colCART_NOME_PRODOTTO;

    @FXML
    public TableColumn<Prodotto, Float> colCART_PREZZO_PRODOTTO;

    @FXML
    public TableColumn<Prodotto, Integer> colCART_QUANTITA_PRODOTTO;


    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(ClienteHomePageAnchor, "loginPage.fxml");
    }
    @FXML
    public void goToProfilePage() throws  IOException {
        new SceneSwitch(ClienteHomePageAnchor, "profilePage.fxml");
    }
    @FXML
    private void showProducts() {
        experia.coffee.experiacoffee.data.ProductQuery query = new experia.coffee.experiacoffee.data.ProductQuery();
        ObservableList<Prodotto> list = query.getProductList();
        col_ID_FORNITURA.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("ID_FORNITURA"));
        col_ID_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("ID_PRODOTTO"));
        col_PROVENIENZA.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("PROVENIENZA"));
        col_QUANTITA.setCellValueFactory(new PropertyValueFactory<Prodotto, Integer>("QUANTITA"));
        col_PREZZO_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Float>("PREZZO_PRODOTTO"));
        colNOME_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("NOME_PRODOTTO"));
        productView.setItems(list);
    }

    @FXML
    public void showCart(String emailCliente) {
        experia.coffee.experiacoffee.data.ProductQuery query = new experia.coffee.experiacoffee.data.ProductQuery();
        ObservableList<Prodotto> list = query.getUserCartList(emailCliente);
        colCART_NOME_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("NOME_PRODOTTO"));
        colCART_PREZZO_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Float>("PREZZO_PRODOTTO"));
        colCART_QUANTITA_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Integer>("QUANTITA"));
        cartView.setItems(list);
    }

    @FXML
    public void openCheckoutPage() throws IOException {
        new SceneSwitch(ClienteHomePageAnchor, "checkoutPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        showProducts();

        if (utente != null) {

            String nome = utente.getNAME();
            String cognome = utente.getSURNAME();
            String email = utente.getEMAIL();

            showCart(email);

            welcomeLabel.setText("Benvenuto, " + nome + " " + cognome + "!");

        } else {
            System.out.println("L'utente e' null nella Home page");
        }
    }
}
