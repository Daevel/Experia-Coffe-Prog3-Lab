package experia.coffee.experiacoffee.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
