package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.model.Utente;

import java.sql.PreparedStatement;

public class SignupQuery {

    private DBConnection c = new DBConnection();

    public boolean signUpUser(Utente utente) {
        try {
            c.getDBConn();
            String query = "INSERT INTO `tbl_cliente`\n" +
                    "(`NOME`,\n" +
                    "`COGNOME`,\n" +
                    "`EMAIL`,\n" +
                    "`CELLULARE`,\n" +
                    "`CITTA`,\n" +
                    "`VIA`,\n" +
                    "`N_CIVICO`,\n" +
                    "`CAP`,\n" +
                    "`UTENTE_PASSWORD`,\n" +
                    "`NUM_CARTA`,\n" +
                    "`CVV_CARTA`,\n" +
                    "`INTESTATARIO_CARTA`,\n" +
                    "`SCADENZA_CARTA`" +
                    "`CODICE_FISCALE`" +
                    "`DATA_DI_NASCITA`)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = c.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, utente.getNAME());
                preparedStatement.setString(2, utente.getSURNAME());
                preparedStatement.setString(3, utente.getEMAIL());
                preparedStatement.setString(4, utente.getPASSWORD());
                preparedStatement.setString(5, utente.getCELLULARE());
                preparedStatement.setString(6, utente.getCITTA());
                preparedStatement.setString(7, utente.getVIA());
                preparedStatement.setString(8, utente.getN_CIVICO());
                preparedStatement.setString(9, utente.getCAP());
                preparedStatement.setString(10, utente.getNUM_CARTA());
                preparedStatement.setString(11, utente.getCVV_CARTA());
                preparedStatement.setString(12, utente.getINTESTATARIO_CARTA());
                preparedStatement.setString(13, utente.getSCADENZA_CARTA());
                preparedStatement.setString(14, utente.getCODICE_FISCALE());
                preparedStatement.setString(15, utente.getDATA_DI_NASCITA());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
