package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public Button profilePageButton;

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

    private Prodotto prodotto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        showProducts();

        if (utente != null) {

            String nome = utente.getNAME();
            String cognome = utente.getSURNAME();

            welcomeLabel.setText("Benvenuto, " + nome + " " + cognome + "!");

        } else {
            System.out.println("L'utente e' null nella Home page");
        }



    }
}
