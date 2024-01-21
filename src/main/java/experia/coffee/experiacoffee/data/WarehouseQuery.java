package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Magazzino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class WarehouseQuery {
    private DBConnection c = new DBConnection();

    public ObservableList<Magazzino> getWarehouseList () {
        ObservableList<experia.coffee.experiacoffee.model.Magazzino> warehouseList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tbl_magazzino;";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Magazzino s;

            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Magazzino(rs.getString("CODICE_MAGAZZINO"), rs.getString("ID_PRODOTTO"), rs.getInt("QUANTITA_PRODOTTO"), rs.getString("NOME_PRODOTTO"), rs.getString("NOME_MAGAZZINO"));
                warehouseList.add(s);
            }

            rs.close();
            st.close();

            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouseList;
    }
}
