package experia.coffee.experiacoffee.validation;

public class PasswordValidation {
    public static boolean checkPasswordIntegrity(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}
