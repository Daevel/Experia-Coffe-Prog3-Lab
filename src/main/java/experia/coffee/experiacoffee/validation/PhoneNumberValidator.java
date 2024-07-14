package experia.coffee.experiacoffee.validation;

public class PhoneNumberValidator {

    // Lunghezza minima e massima per i numeri di telefono di casa
    private static final int MIN_LANDLINE_LENGTH = 6;
    private static final int MAX_LANDLINE_LENGTH = 10;

    // Lunghezza esatta per i numeri di cellulare
    private static final int MOBILE_LENGTH = 10;

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        // Rimuove eventuali spazi, trattini o altri caratteri non numerici
        phoneNumber = phoneNumber.replaceAll("\\s+|-", "");

        // Controlla che il numero contenga solo cifre
        if (!phoneNumber.matches("\\d+")) {
            return false;
        }

        // Controlla la lunghezza del numero
        int length = phoneNumber.length();
        return (length == MOBILE_LENGTH || (length >= MIN_LANDLINE_LENGTH && length <= MAX_LANDLINE_LENGTH));
    }

}
