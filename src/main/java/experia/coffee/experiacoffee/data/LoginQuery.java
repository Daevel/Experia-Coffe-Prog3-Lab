package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.LoginResult;
import experia.coffee.experiacoffee.model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginQuery {

    private DBConnection c = new DBConnection();

    public LoginResult loginUser(Utente utente) {
        try {
            c.getDBConn();
            String query = "SELECT * FROM tbl_cliente WHERE EMAIL = ? AND UTENTE_PASSWORD = ?";
            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, utente.getEMAIL());
                preparedStatement.setString(2, utente.getPASSWORD());



                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   if(resultSet.next()) {
                       String ruolo = resultSet.getString("RUOLO");

                       return new LoginResult(true, ruolo);
                   } else {
                       return new LoginResult(false, null);
                   }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResult(false, null);
        }
    }


}
