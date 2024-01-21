package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Ordine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

                s = new experia.coffee.experiacoffee.model.Ordine(rs.getString("ID_ORDINE"), rs.getInt("NUMERO_ORDINE"), rs.getString("ORDINATO_DA"), rs.getString("DESTINAZIONE"), rs.getString("FILIALE_IN_CARICO"), rs.getString("CORRIERE_IN_CARICO"));
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
}
