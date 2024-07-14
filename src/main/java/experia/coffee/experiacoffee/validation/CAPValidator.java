package experia.coffee.experiacoffee.validation;

public class CAPValidator {

    private static final int CAP_LENGTH = 5;

    public static boolean isValidCAP(String cap) {
        if (cap == null || cap.isEmpty()) {
            return false;
        }

        return cap.matches("\\d{" + CAP_LENGTH + "}");
    }
}
