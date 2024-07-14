package experia.coffee.experiacoffee.validation;

import java.util.regex.Pattern;

public class FiscalcodeValidator {

    // Pattern regex per il codice fiscale italiano
    private static final String CODICE_FISCALE_PATTERN = "^[A-Z]{6}[0-9]{2}[A-EHLMPR-T][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    // Mappatura delle lettere del mese (per il controllo facoltativo)
    private static final String MONTH_LETTERS = "ABCDEHLMPRST";

    public static boolean isValidCodiceFiscale(String codiceFiscale) {
        // Controlla la lunghezza del codice fiscale
        if (codiceFiscale == null || codiceFiscale.length() != 16) {
            return false;
        }

        // Controlla se il codice fiscale rispetta il pattern
        if (!Pattern.matches(CODICE_FISCALE_PATTERN, codiceFiscale)) {
            return false;
        }

        // Controlla la validità della lettera del mese
        char mese = codiceFiscale.charAt(8);
        if (MONTH_LETTERS.indexOf(mese) == -1) {
            return false;
        }

        // Altri controlli specifici possono essere aggiunti qui

        return true;
    }

}
