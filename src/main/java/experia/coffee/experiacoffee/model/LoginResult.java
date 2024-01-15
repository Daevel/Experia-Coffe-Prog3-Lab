package experia.coffee.experiacoffee.model;

public class LoginResult {
    private boolean success;
    private Utente user;

    public LoginResult(boolean success, Utente user) {
        this.success = success;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public Utente getUser() {
        return user;
    }
}
