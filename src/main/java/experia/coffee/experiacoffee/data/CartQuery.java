package experia.coffee.experiacoffee.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CartQuery {

    private DBConnection c = new DBConnection();

    public void createCart(String cartId, String userEmail) {
        try {
            c.getDBConn();
            String sql = "INSERT INTO tbl_carrello (ID, EMAIL_CLIENTE, PREZZO_TOTALE, QUANTITA_PRODOTTO, DATA_ACQUISTO) VALUES (?, ?, 0, 0, '1990-01-01')";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(sql)) {

                 preparedStatement.setString(1, cartId);
                 preparedStatement.setString(2, userEmail);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0) {
                    System.out.println("Carrello creato correttamente");
                } else {
                    System.out.println("Errore nella creazione del carrello");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
