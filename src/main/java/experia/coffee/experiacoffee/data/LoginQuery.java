package experia.coffee.experiacoffee.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginQuery {

    private DBConnection c = new DBConnection();

    public boolean loginUser(experia.coffee.experiacoffee.model.Utente utente) {
        try {
            c.getDBConn();
            String query = "SELECT * FROM tbl_cliente WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, utente.getEmail());
                preparedStatement.setString(2, utente.getPassword());

                System.out.println(utente.getEmail());
                System.out.println(utente.getPassword());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
