package experia.coffee.experiacoffee.validation;

public class CardOwnerValidator {
    public static boolean isCardOwnerNameValid(String firstName, String lastName, String cardOwnerName) {
        if (firstName == null || lastName == null || cardOwnerName == null) {
            return false;
        }

        String fullName = firstName.trim() + " " + lastName.trim();
        return fullName.equalsIgnoreCase(cardOwnerName.trim());
    }
}
