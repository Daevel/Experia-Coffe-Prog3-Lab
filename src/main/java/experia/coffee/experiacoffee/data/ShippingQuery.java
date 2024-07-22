package experia.coffee.experiacoffee.data;

import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class ShippingQuery {

    // Metodo per ottenere la connessione al database
    private Connection getDBConnection() {
        // Implementa la logica per ottenere la connessione al database
        // Ad esempio, usando un DataSource o DriverManager
        return DBConnection.getCon();
    }

    // Metodo per generare una partita IVA casuale
    private String generateRandomPartitaIva() {
        Random random = new Random();
        StringBuilder partitaIva = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            partitaIva.append(random.nextInt(10));
        }
        return partitaIva.toString();
    }

    // Metodo per generare una stringa casuale per il numero di tracciamento e l'ordine
    private String generateRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10).toUpperCase();
    }

    // Metodo per inserire una nuova spedizione e restituire il numero di tracciamento
    public String insertNewSpedizione() {
        String numeroTracciamento = "SPZ-" + generateRandomString();
        String partitaIvaCorriere = "00000000000";
        String numeroOrdine = "ORD-" + generateRandomString();

        String sql = "INSERT INTO tbl_spedizione (NUMERO_TRACCIAMENTO, P_IVA_CORRIERE, NUMERO_ORDINE, DATA_PARTENZA, DATA_ARRIVO) " +
                "VALUES (?, ?, ?, ?, ?)";

        LocalDate today = LocalDate.now();
        Date dataPartenza = Date.valueOf(today);
        Date dataArrivo = Date.valueOf(today.plusDays(1));

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, numeroTracciamento);
            preparedStatement.setString(2, partitaIvaCorriere);
            preparedStatement.setString(3, numeroOrdine);
            preparedStatement.setDate(4, dataPartenza);
            preparedStatement.setDate(5, dataArrivo);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Spedizione inserita correttamente");
                return numeroTracciamento;
            } else {
                System.out.println("Errore nell'inserimento della spedizione");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();  // Usa un metodo di gestione degli errori appropriato
            return null;
        }
    }

    public String getCodiceSpedizione(String numeroOrdine) {
        String codiceSpedizione = null;
        String sql = "SELECT NUMERO_TRACCIAMENTO FROM tbl_spedizione WHERE NUMERO_ORDINE = ?";

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, numeroOrdine);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    codiceSpedizione = resultSet.getString("NUMERO_TRACCIAMENTO");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Usa un metodo di gestione degli errori appropriato
        }

        return codiceSpedizione;
    }
}
