package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Ordine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderQuery {

    private final DBConnection c = new DBConnection();


    public ObservableList<Ordine> getOrderListByClient(String email) {
        ObservableList<experia.coffee.experiacoffee.model.Ordine> orderStatus = FXCollections.observableArrayList();
        try {
            c.getDBConn();
            String query = "SELECT \n" +
                    "o.ID,\n" +
                    "    c.EMAIL AS ORDINATO_DA,\n" +
                    "    c.VIA AS DESTINAZIONE,\n" +
                    "    o.STATO_ORDINE\n" +
                    "FROM\n" +
                    "tbl_cliente c\n" +
                    "JOIN tbl_carrello crt ON c.EMAIL = crt.EMAIL_CLIENTE\n" +
                    "JOIN tbl_ordine o ON crt.ID = o.ID_CARRELLO\n" +
                    "\tWHERE c.EMAIL = ?;";
            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, email);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        experia.coffee.experiacoffee.model.Ordine s;
                        s = new experia.coffee.experiacoffee.model.Ordine(rs.getInt("ID"), null, rs.getString("ORDINATO_DA"), rs.getString("DESTINAZIONE"), null, null, rs.getString("STATO_ORDINE"));
                        orderStatus.add(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DBConnection.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderStatus;
    }


    public ObservableList<Ordine> getOrderList () {
        ObservableList<experia.coffee.experiacoffee.model.Ordine> productList = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n" +
                    "    o.ID,\n" +
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
                    "    tbl_gestito_da g ON o.ID = g.CODICE_ORDINE\n" +
                    "LEFT JOIN\n" +
                    "    tbl_filiale f ON g.ID_FILIALE = f.CODICE_ZONA_FILIALE\n" +
                    "LEFT JOIN\n" +
                    "\ttbl_emette_spedizione es ON es.CODICE_ZONA_FILIALE = f.CODICE_ZONA_FILIALE\n" +
                    "LEFT JOIN\n" +
                    "\ttbl_spedizione s ON s.NUMERO_TRACCIAMENTO = es.NUMERO_TRACCIAMENTO\n" +
                    "LEFT JOIN\n" +
                    "    tbl_corriere co ON s.P_IVA_CORRIERE = co.P_IVA;\n";
            c.getDBConn();
            Statement st = DBConnection.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Ordine s;

            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Ordine(rs.getInt("ID"), rs.getInt("NUMERO_ORDINE"), rs.getString("ORDINATO_DA"), rs.getString("DESTINAZIONE"), rs.getString("FILIALE_IN_CARICO"), rs.getString("CORRIERE_IN_CARICO"), rs.getString("STATO_ORDINE"));
                productList.add(s);
            }

            rs.close();
            st.close();

            DBConnection.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }


    public boolean createOrder (String userEmail) {
        try {
            c.getDBConn();
            String sql = "INSERT INTO tbl_ordine (FATTURA, NUMERO_ORDINE, ID_CARRELLO, INDIRIZZO_SPEDIZIONE) VALUES ('EMPTY', 0, (SELECT ID FROM tbl_carrello WHERE EMAIL_CLIENTE = ?), (SELECT VIA from tbl_cliente WHERE EMAIL = ?))";
            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(sql)) {

                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, userEmail);

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

    public boolean updateOrder(int id, int numeroOrdine, String filialeInCarico, String corriereInCarico, String statoOrdine) {
        try {
            c.getDBConn();
            String sql = "UPDATE tbl_cliente c\n" +
                    "JOIN tbl_carrello ca ON c.EMAIL = ca.EMAIL_CLIENTE\n" +
                    "JOIN tbl_ordine o ON ca.ID = o.ID_CARRELLO\n" +
                    "JOIN tbl_gestito_da gda ON o.ID = gda.CODICE_ORDINE\n" +
                    "JOIN tbl_filiale f ON gda.ID_FILIALE = f.CODICE_ZONA_FILIALE\n" +
                    "JOIN tbl_emette_spedizione ems ON f.CODICE_ZONA_FILIALE = ems.CODICE_ZONA_FILIALE\n" +
                    "JOIN tbl_spedizione s ON ems.NUMERO_TRACCIAMENTO = s.NUMERO_TRACCIAMENTO\n" +
                    "JOIN tbl_corriere cor ON cor.P_IVA = s.P_IVA_CORRIERE\n" +
                    "\n" +
                    "SET\n" +
                    "    o.NUMERO_ORDINE = ?,\n" +
                    "    o.STATO_ORDINE = ?,\n" +
                    "    f.NOME_FILIALE = ?,\n" +
                    "    cor.NOME = ?\n" +
                    "WHERE o.ID = ?";
            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(sql)) {

                preparedStatement.setInt(1, numeroOrdine);
                preparedStatement.setString(2, statoOrdine);
                preparedStatement.setString(3, filialeInCarico);
                preparedStatement.setString(4, corriereInCarico);
                preparedStatement.setInt(5, id);

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

    public boolean deleteOrder(int id) {
        c.getDBConn();
        String sql = "DELETE FROM tbl_ordine WHERE ID = ?";

        try(PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
