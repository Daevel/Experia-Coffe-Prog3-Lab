package experia.coffee.experiacoffee.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductQuery {
    private DBConnection c = new DBConnection();
    public ObservableList<experia.coffee.experiacoffee.model.Prodotto> getProductList () {
        ObservableList<experia.coffee.experiacoffee.model.Prodotto> productList = FXCollections.observableArrayList();
        try {
            String query = "select * from tbl_prodotto order by NOME_PRODOTTO asc";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Prodotto s;
            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Prodotto(rs.getString("NOME_PRODOTTO"),rs.getFloat("PREZZO_PRODOTTO"), rs.getInt("QUANTITA"),rs.getString("PROVENIENZA"),rs.getString("ID_FORNITURA"), rs.getString("ID_PRODOTTO"));
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

    public ObservableList<experia.coffee.experiacoffee.model.Prodotto> getUserCartList (String emailCliente) {
        ObservableList<experia.coffee.experiacoffee.model.Prodotto> productList = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n" +
                    "    tbl_prodotto.NOME_PRODOTTO,\n" +
                    "    tbl_prodotto.PREZZO_PRODOTTO,\n" +
                    "    tbl_prodotto.QUANTITA\n" +
                    "FROM\n" +
                    "    tbl_carrello\n" +
                    "JOIN\n" +
                    "    tbl_aggiunto_in ON tbl_carrello.ID = tbl_aggiunto_in.ID_CARRELLO\n" +
                    "JOIN\n" +
                    "    tbl_prodotto ON tbl_aggiunto_in.ID_PRODOTTO = tbl_prodotto.ID_PRODOTTO\n" +
                    "WHERE\n" +
                    "    tbl_carrello.EMAIL_CLIENTE = ? ;\n";
            c.getDBConn();

            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, emailCliente);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    experia.coffee.experiacoffee.model.Prodotto s;
                    while (rs.next()) {
                        s = new experia.coffee.experiacoffee.model.Prodotto(rs.getString("NOME_PRODOTTO"),rs.getFloat("PREZZO_PRODOTTO"), rs.getInt("QUANTITA"),null,null);
                        productList.add(s);
                    }
                }
            }
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
