package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.*;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.ObserverPattern.Prodotto;
import experia.coffee.experiacoffee.model.ObserverPattern.ProdottoSubscriber;
import experia.coffee.experiacoffee.model.ObserverPattern.ProductObserver;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.SingletonPattern.ValoreTotaleSingleton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ClienteHomePage implements Initializable {

    @FXML
    private AnchorPane ClienteHomePageAnchor;
    @FXML
    public ImageView favicon;
    @FXML
    public Button returnToLoginPageButton;
    @FXML
    public Button profilePageButton, checkoutPageButton, orderPageButton;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label totalAmount;
    @FXML
    public Label productObserverMessage;
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
    private ObservableList<Prodotto> productlist;
    private ObservableList<Prodotto> cartList;
    private float valoreTotale = 0.0f;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        updateTotalAmount();
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

    @FXML
    public void returnToLoginPage() throws IOException {
        new SceneSwitch(ClienteHomePageAnchor, "loginPage.fxml");
    }
    @FXML
    public void goToProfilePage() throws  IOException {
        new SceneSwitch(ClienteHomePageAnchor, "profilePage.fxml");
    }
    @FXML
    public void goToOrderPage() throws IOException {
        new SceneSwitch(ClienteHomePageAnchor, "orderStatusPage.fxml");
    }

    @FXML
    private void showProducts() {
        experia.coffee.experiacoffee.data.ProductQuery query = new experia.coffee.experiacoffee.data.ProductQuery();

        productlist = query.getProductList();

        productView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Prodotto prodottoSelezionato = productView.getSelectionModel().getSelectedItem();
                if(prodottoSelezionato != null) {
                    cartList.add(prodottoSelezionato);
                    ProductObserver addPrd = new ProdottoSubscriber("addlabel");
                    prodottoSelezionato.register(addPrd);
                    addPrd.setSubject(prodottoSelezionato);
                    addPrd.update();
                    productObserverMessage.setText(prodottoSelezionato.postMessage("Aggiunto il seguente prodotto: " + prodottoSelezionato.getNOME_PRODOTTO()));
                    valoreTotale += prodottoSelezionato.getPREZZO_PRODOTTO();
                    updateTotalAmount();
                }
            }
        });

        col_ID_FORNITURA.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("ID_FORNITURA"));
        col_ID_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("ID_PRODOTTO"));
        col_PROVENIENZA.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("PROVENIENZA"));
        col_QUANTITA.setCellValueFactory(new PropertyValueFactory<Prodotto, Integer>("QUANTITA"));
        col_PREZZO_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Float>("PREZZO_PRODOTTO"));
        colNOME_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("NOME_PRODOTTO"));
        productView.setItems(productlist);
    }

    @FXML
    public void showCart(String emailCliente) {
        experia.coffee.experiacoffee.data.ProductQuery query = new experia.coffee.experiacoffee.data.ProductQuery();
        cartList = query.getUserCartList(emailCliente);

        cartView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Prodotto prodottoSelezionato = cartView.getSelectionModel().getSelectedItem();
                if(prodottoSelezionato != null) {
                    cartList.remove(prodottoSelezionato);
                    ProductObserver removePrd = new ProdottoSubscriber("rmvLabel");
                    prodottoSelezionato.register(removePrd);
                    removePrd.setSubject(prodottoSelezionato);
                    removePrd.update();
                    productObserverMessage.setText(prodottoSelezionato.postMessage("Rimosso il seguente prodotto: " + prodottoSelezionato.getNOME_PRODOTTO()));
                    valoreTotale -= prodottoSelezionato.getPREZZO_PRODOTTO();
                    updateTotalAmount();
                }
            }
        });

        colCART_NOME_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("NOME_PRODOTTO"));
        colCART_PREZZO_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Float>("PREZZO_PRODOTTO"));
        colCART_QUANTITA_PRODOTTO.setCellValueFactory(new PropertyValueFactory<Prodotto, Integer>("QUANTITA"));
        cartView.setItems(cartList);
    }

    @FXML
    public void openCheckoutPage() throws IOException {
        ValoreTotaleSingleton.getInstance().setValoreTotale(valoreTotale);
        new SceneSwitch(ClienteHomePageAnchor, "checkoutPage.fxml");
    }

    private void updateTotalAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        totalAmount.setText("Totale: €" + decimalFormat.format(valoreTotale));
    }
}
