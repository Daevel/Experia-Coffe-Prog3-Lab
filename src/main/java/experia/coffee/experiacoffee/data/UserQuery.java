package experia.coffee.experiacoffee.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserQuery {
    private DBConnection c = new DBConnection();

    public boolean changeEmail (String email, String fiscalCode) {
        try {
            String query = "UPDATE tbl_cliente SET EMAIL = ? WHERE CODICE_FISCALE = ?";
            c.getDBConn();

            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, fiscalCode);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                c.closeConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean resetPassword (String email, String password, String role) {
        if(role.equalsIgnoreCase("cliente")) {
            try {
                String query = "UPDATE tbl_cliente SET UTENTE_PASSWORD = ? WHERE EMAIL = ?";
                c.getDBConn();

                try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                    preparedStatement.setString(1, password);
                    preparedStatement.setString(2, email);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if(rowsAffected >0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    c.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (role.equalsIgnoreCase("dipendente")) {
            try {
                String query = "UPDATE tbl_dipendente SET UTENTE_PASSWORD = ? WHERE EMAIL = ?";
                c.getDBConn();

                try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                    preparedStatement.setString(1, password);
                    preparedStatement.setString(2, email);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if(rowsAffected >0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    c.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
