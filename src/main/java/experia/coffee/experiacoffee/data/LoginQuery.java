package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.LoginResult;
import experia.coffee.experiacoffee.model.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginQuery {

    private DBConnection c = new DBConnection();

    public Utente loginUser(Utente utente) {
        try {
            c.getDBConn();
            String query = "SELECT * FROM tbl_cliente WHERE EMAIL = ? AND UTENTE_PASSWORD = ?";
            String employeeQuery = "SELECT * FROM tbl_dipendente WHERE EMAIL = ? AND UTENTE_PASSWORD = ?";


            // se nella mail è presente il dominio @experiacoffee allora è un dipendente
            if (utente.getEMAIL().contains("@experiacoffee")) {
                try (PreparedStatement employeePreparedStatement = c.getCon().prepareStatement(employeeQuery)) {

                    employeePreparedStatement.setString(1, utente.getEMAIL());
                    employeePreparedStatement.setString(2, utente.getPASSWORD());

                    try (ResultSet employeeResultSet = employeePreparedStatement.executeQuery()) {
                        if (employeeResultSet.next()) {
                            String ruolo = employeeResultSet.getString("RUOLO");
                            String nome = employeeResultSet.getString("NOME");
                            String cognome = employeeResultSet.getString("COGNOME");

                            Utente resultUtente = new Utente.UtenteBuilder(utente.getEMAIL(), utente.getPASSWORD())
                                    .setRUOLO(ruolo)
                                    .setNAME(nome)
                                    .setSURNAME(cognome)
                                    .build();

                            return new LoginResult(true, resultUtente).getUser();
                        } else {
                            return new LoginResult(false, null).getUser();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new LoginResult(false, null).getUser();
                }
            } else {
                try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {

                    preparedStatement.setString(1, utente.getEMAIL());
                    preparedStatement.setString(2, utente.getPASSWORD());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                                String ruolo = resultSet.getString("RUOLO");
                                String nome = resultSet.getString("NOME");
                                String cognome = resultSet.getString("COGNOME");

                            Utente resultUtente = new Utente.UtenteBuilder(utente.getEMAIL(), utente.getPASSWORD())
                                    .setRUOLO(ruolo)
                                    .setNAME(nome)
                                    .setSURNAME(cognome)
                                    .build();

                            return new LoginResult(true, resultUtente).getUser();
                        } else {
                            return new LoginResult(false, null).getUser();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new LoginResult(false, null).getUser();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResult(false, null).getUser();
        }
    }
}
