package experia.coffee.experiacoffee.validation;

import java.util.regex.Pattern;

public class EmailValidation {

    private static final String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean patternMatches(String email) {
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public static boolean detectSameEmailError(String newEmail, String oldEmail) {
        return newEmail.equalsIgnoreCase(oldEmail);
    }
}
