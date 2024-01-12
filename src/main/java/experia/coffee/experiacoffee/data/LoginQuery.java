package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginQuery {

    private DBConnection c = new DBConnection();

    public boolean loginUser(Utente utente) {
        try {
            c.getDBConn();
            String query = "SELECT * FROM tbl_cliente WHERE EMAIL = ? AND UTENTE_PASSWORD = ?";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, utente.getEMAIL());
                preparedStatement.setString(2, utente.getPASSWORD());

                System.out.println(utente.getEMAIL());
                System.out.println(utente.getPASSWORD());

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
