package experia.coffee.experiacoffee.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class AppQuery {
    private DBConnection c = new DBConnection();
    public void addProduct(experia.coffee.experiacoffee.model.Product product) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into products(firstname, secondname, lastname)values(?,?,?)");
            ps.setString(1, product.getFirstname());
            ps.setString(2, product.getSecondname());
            ps.setString(3, product.getLastname());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<experia.coffee.experiacoffee.model.Product> getStudentList () {
        ObservableList<experia.coffee.experiacoffee.model.Product> productList = FXCollections.observableArrayList();
        try {
            String query = "select id, firstname, secondname, lastname from products order by lastname asc";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Product s;
            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Product(rs.getInt("id"), rs.getString("firstname"), rs.getString("secondname"), rs.getString("lastname"));
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

    public void updateProducts(experia.coffee.experiacoffee.model.Product product) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("UPDATE `products`\n"
                + "SET \n"
                + "`firstname` = ?,\n"
                + "`secondname` = ?, \n"
                + "`lastname` = ?, \n"
                + "WHERE `id` = ?");
            ps.setString(1, product.getFirstname());
            ps.setString(2, product.getSecondname());
            ps.setString(3, product.getLastname());
            ps.setInt(4, product.getId());
            ps.execute();
            ps.close();
            c.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProduct(experia.coffee.experiacoffee.model.Product product) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM `product` \n"
                + "WHERE id= ?;");
            ps.setInt(1, product.getId());
            ps.execute();
            ps.close();
            c.closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
