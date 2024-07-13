package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.ObserverPattern.Prodotto;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductQuery {
    private final DBConnection c = new DBConnection();
    public ObservableList<Prodotto> getProductList () {
        ObservableList<Prodotto> productList = FXCollections.observableArrayList();
        try {
            String query = "select * from tbl_prodotto order by NOME_PRODOTTO asc";
            c.getDBConn();
            Statement st = DBConnection.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            Prodotto s;
            while (rs.next()) {
                s = new Prodotto(rs.getString("NOME_PRODOTTO"),rs.getFloat("PREZZO_PRODOTTO"), rs.getInt("QUANTITA"),rs.getString("PROVENIENZA"),rs.getString("ID_FORNITURA"), rs.getString("ID_PRODOTTO"));
                productList.add(s);
            }
            rs.close();
            st.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return productList;
    }

    public ObservableList<Prodotto> getUserCartList (String emailCliente) {
        ObservableList<Prodotto> productList = FXCollections.observableArrayList();
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

            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, emailCliente);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    Prodotto s;
                    while (rs.next()) {
                        s = new Prodotto(rs.getString("NOME_PRODOTTO"),rs.getFloat("PREZZO_PRODOTTO"), rs.getInt("QUANTITA"),null,null);
                        productList.add(s);
                    }
                } catch (Exception e) {
                    PopupWindowError.handleException(e);
                }
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            } finally {
                DBConnection.closeConnection();
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return productList;
    }

    public ObservableList<Prodotto> getTotalAmount (String emailCliente) {
        ObservableList<Prodotto> amountProduct = FXCollections.observableArrayList();
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

            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, emailCliente);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    Prodotto s;
                    while (rs.next()) {
                        s = new Prodotto(rs.getString("NOME_PRODOTTO"),rs.getFloat("PREZZO_PRODOTTO"), rs.getInt("QUANTITA"),null,null);
                        amountProduct.add(s);
                    }
                } catch (Exception e) {
                    PopupWindowError.handleException(e);
                }
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            } finally {
                DBConnection.closeConnection();
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return amountProduct;
    }
}
