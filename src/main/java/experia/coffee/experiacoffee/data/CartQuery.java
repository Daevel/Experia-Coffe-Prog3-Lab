package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CartQuery {

    private final DBConnection c = new DBConnection();

    public void createCart(String userEmail) {
        try {
            c.getDBConn();
            String sql = "INSERT INTO tbl_carrello (EMAIL_CLIENTE, PREZZO_TOTALE, QUANTITA_PRODOTTO, DATA_ACQUISTO) VALUES (?, 0, 0, '1990-01-01')";
            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(sql)) {

                 preparedStatement.setString(1, userEmail);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0) {
                    PopupWindow.showAlert(Alert.AlertType.CONFIRMATION, Constants.CART_CREATE_SUCCESS, Constants.CART);
                } else {
                    PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.CART_CREATE_FAILED, Constants.CART);
                }

            } catch (Exception e) {
                PopupWindowError.handleException(e);
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
    }
}
