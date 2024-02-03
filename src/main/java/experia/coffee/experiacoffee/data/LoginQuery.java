package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.LoginResult;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;

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
                            String codiceFiscale = employeeResultSet.getString("CODICE_FISCALE");
                            String dataDiNascita = employeeResultSet.getString("DATA_DI_NASCITA");

                            Utente resultUtente = new Utente.UtenteBuilder(utente.getEMAIL(), utente.getPASSWORD())
                                    .setRUOLO(ruolo)
                                    .setNAME(nome)
                                    .setSURNAME(cognome)
                                    .setCODICE_FISCALE(codiceFiscale)
                                    .setDATA_DI_NASCITA(dataDiNascita)
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
                                String codiceFiscale = resultSet.getString("CODICE_FISCALE");
                                String dataDiNascita = resultSet.getString("DATA_DI_NASCITA");
                                String cellulare = resultSet.getString("CELLULARE");
                                String citta = resultSet.getString("CITTA");
                                String via = resultSet.getString("VIA");
                                String n_civico = resultSet.getString("N_CIVICO");
                                String cap = resultSet.getString("CAP");
                                String num_carta = resultSet.getString("NUM_CARTA");
                                String cvv_carta = resultSet.getString("CVV_CARTA");
                                String intestatario_carta = resultSet.getString("INTESTATARIO_CARTA");
                                String scadenza_carta = resultSet.getString("SCADENZA_CARTA");

                            Utente resultUtente = new Utente.UtenteBuilder(utente.getEMAIL(), utente.getPASSWORD())
                                    .setRUOLO(ruolo)
                                    .setNAME(nome)
                                    .setSURNAME(cognome)
                                    .setCODICE_FISCALE(codiceFiscale)
                                    .setDATA_DI_NASCITA(dataDiNascita)
                                    .setCELLULARE(cellulare)
                                    .setCITTA(citta)
                                    .setVIA(via)
                                    .setN_CIVICO(n_civico)
                                    .setCAP(cap)
                                    .setNUM_CARTA(num_carta)
                                    .setCVV_CARTA(cvv_carta)
                                    .setINTESTATARIO_CARTA(intestatario_carta)
                                    .setSCADENZA_CARTA(scadenza_carta)
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
