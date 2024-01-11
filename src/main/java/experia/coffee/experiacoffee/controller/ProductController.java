package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.data.AppQuery;
import experia.coffee.experiacoffee.model.Product;
import experia.coffee.experiacoffee.model.SceneSwitch;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        showProducts();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    public TextField fieldFirstName;
    @FXML
    public TextField fieldSecondName;
    @FXML
    public TextField fieldLastName;
    @FXML
    public Button btnNew;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnDelete;

    @FXML
    public Button btnLogin;

    @FXML
    public TableView<Product> tableView;

    @FXML
    public TableColumn<Product, Integer> colId;
    @FXML
    public TableColumn<Product, String> colFirstname;
    @FXML
    public TableColumn<Product, String> colSecondName;
    @FXML
    public TableColumn<Product, String> colLastName;

    private Product product;


    @FXML
    private AnchorPane sceneAnchorPane;

    @FXML
    public void openLoginPage() throws IOException {
            new SceneSwitch(sceneAnchorPane, "loginPage.fxml");
    }

    @FXML
    private void addProduct() {
        experia.coffee.experiacoffee.model.Product product = new experia.coffee.experiacoffee.model.Product(fieldFirstName.getText(), fieldSecondName.getText(), fieldLastName.getText());
        experia.coffee.experiacoffee.data.AppQuery query = new AppQuery();
        query.addProduct(product);
    }

    @FXML
    private void showProducts() {
        experia.coffee.experiacoffee.data.AppQuery query = new experia.coffee.experiacoffee.data.AppQuery();
        ObservableList<Product> list = query.getStudentList();
        colId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<Product, String>("firstname"));
        colSecondName.setCellValueFactory(new PropertyValueFactory<Product, String>("secondname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Product, String>("lastname"));
        tableView.setItems(list);
    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        try {
            Product product = tableView.getSelectionModel().getSelectedItem();
            product = new Product(product.getId(), product.getFirstname(), product.getSecondname(), product.getLastname());
            this.product = product;
            fieldFirstName.setText(product.getFirstname());
            fieldSecondName.setText(product.getSecondname());
            fieldLastName.setText(product.getLastname());
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnNew.setDisable(true);
            btnSave.setDisable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updateProducts() {
        try {
            experia.coffee.experiacoffee.data.AppQuery query = new experia.coffee.experiacoffee.data.AppQuery();
            experia.coffee.experiacoffee.model.Product product = new experia.coffee.experiacoffee.model.Product(this.product.getId(), fieldFirstName.getText(), fieldSecondName.getText(), fieldLastName.getText());
            query.updateProducts(product);
            showProducts();
            clearFields();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void deleteProducts() {
        try {
            experia.coffee.experiacoffee.data.AppQuery query = new experia.coffee.experiacoffee.data.AppQuery();
            experia.coffee.experiacoffee.model.Product product = new experia.coffee.experiacoffee.model.Product(this.product.getId(), fieldFirstName.getText(), fieldSecondName.getText(), fieldLastName.getText());
            query.deleteProduct(product);
            deleteProducts();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        fieldFirstName.setText("");
        fieldLastName.setText("");
        fieldSecondName.setText("");
    }

    @FXML
    private void clickNew() {
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        clearFields();
        btnSave.setDisable(false);
    }

}
