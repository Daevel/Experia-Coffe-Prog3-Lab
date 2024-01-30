package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Ordine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderQuery {

    private DBConnection c = new DBConnection();

    public ObservableList<Ordine> getOrderList () {
        ObservableList<experia.coffee.experiacoffee.model.Ordine> productList = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n" +
                    "    o.ID_ORDINE,\n" +
                    "    o.NUMERO_ORDINE,\n" +
                    "    o.STATO_ORDINE,\n" +
                    "    c.EMAIL AS ORDINATO_DA,\n" +
                    "    o.INDIRIZZO_SPEDIZIONE AS DESTINAZIONE,\n" +
                    "    f.NOME_FILIALE AS FILIALE_IN_CARICO,\n" +
                    "    co.NOME AS CORRIERE_IN_CARICO\n" +
                    "FROM\n" +
                    "    tbl_ordine o\n" +
                    "JOIN\n" +
                    "    tbl_carrello ca ON o.ID_CARRELLO = ca.ID\n" +
                    "JOIN\n" +
                    "    tbl_cliente c ON ca.EMAIL_CLIENTE = c.EMAIL\n" +
                    "LEFT JOIN\n" +
                    "    tbl_gestito_da g ON o.ID_ORDINE = g.CODICE_ORDINE\n" +
                    "LEFT JOIN\n" +
                    "    tbl_filiale f ON g.ID_FILIALE = f.CODICE_ZONA_FILIALE\n" +
                    "LEFT JOIN\n" +
                    "\ttbl_emette_spedizione es ON es.CODICE_ZONA_FILIALE = f.CODICE_ZONA_FILIALE\n" +
                    "LEFT JOIN\n" +
                    "\ttbl_spedizione s ON s.NUMERO_TRACCIAMENTO = es.NUMERO_TRACCIAMENTO\n" +
                    "LEFT JOIN\n" +
                    "    tbl_corriere co ON s.P_IVA_CORRIERE = co.P_IVA;\n";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Ordine s;

            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Ordine(rs.getString("ID_ORDINE"), rs.getInt("NUMERO_ORDINE"), rs.getString("ORDINATO_DA"), rs.getString("DESTINAZIONE"), rs.getString("FILIALE_IN_CARICO"), rs.getString("CORRIERE_IN_CARICO"), rs.getString("STATO_ORDINE"));
                productList.add(s);
            }

            rs.close();
            st.close();

            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public boolean createOrder (String cartId, String userEmail) {
        try {
            c.getDBConn();
            String sql = "INSERT INTO tbl_ordine (ID_ORDINE, FATTURA, NUMERO_ORDINE, ID_CARRELLO, INDIRIZZO_SPEDIZIONE) VALUES (?, 'EMPTY', 0, (SELECT ID FROM tbl_carrello WHERE EMAIL_CLIENTE = ?), (SELECT VIA from tbl_cliente WHERE EMAIL = ?))";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(sql)) {

                preparedStatement.setString(1, cartId);
                preparedStatement.setString(2, userEmail);
                preparedStatement.setString(3, userEmail);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0) {
                    System.out.println("Ordine creato correttamente");
                    return true;
                } else {
                    System.out.println("Errore nella creazione dell'ordine");
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrder(String emailUser, int numeroOrdine, String filialeInCarico, String corriereInCarico) {
        try {
            c.getDBConn();
            String sql = "UPDATE tbl_gestito_da\n" +
                    "JOIN tbl_filiale ON tbl_gestito_da.ID_FILIALE = tbl_filiale.CODICE_ZONA\n"+
                    "JOIN tbl_ordine ON tbl_gestito_da.CODICE_ORDINE = tbl_ordine.ID_ORDINE\n" +
                    "JOIN tbl_corriere ON tbl_ordine.ID_CARRELLO = tbl_corriere.P_IVA" +
                    " SET tbl_ordine.NUMERO_ORDINE = ?, tbl_filiale.NOME_FILIALE = ?, tbl_corriere.NOME = ? WHERE tbl_cliente.EMAIL = ?;";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(sql)) {

                preparedStatement.setInt(1, numeroOrdine);
                preparedStatement.setString(2, filialeInCarico);
                preparedStatement.setString(3, corriereInCarico);
                preparedStatement.setString(4, emailUser);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0) {
                    System.out.println("Ordine aggiornato correttamente");
                    return true;
                } else {
                    System.out.println("Errore nell'aggiornamento dell'ordine");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
