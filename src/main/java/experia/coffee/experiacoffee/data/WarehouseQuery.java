package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Magazzino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class WarehouseQuery {
    private final DBConnection c = new DBConnection();

    public ObservableList<Magazzino> getWarehouseList () {
        ObservableList<experia.coffee.experiacoffee.model.Magazzino> warehouseList = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n" +
                    "    f.NOME_FILIALE,\n" +
                    "    m.ID_PRODOTTO,\n" +
                    "    m.NOME_PRODOTTO,\n" +
                    "    m.QUANTITA_PRODOTTO\n" +
                    "FROM\n" +
                    "    tbl_filiale f\n" +
                    "JOIN\n" +
                    "    tbl_magazzino m ON f.CODICE_ZONA_FILIALE = m.CODICE_MAGAZZINO;\n";
            c.getDBConn();
            Statement st = DBConnection.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Magazzino s;

            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Magazzino(rs.getString("NOME_FILIALE"), rs.getString("ID_PRODOTTO"), rs.getString("NOME_PRODOTTO"), rs.getInt("QUANTITA_PRODOTTO"));
                warehouseList.add(s);
            }

            rs.close();
            st.close();

            DBConnection.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouseList;
    }
}
