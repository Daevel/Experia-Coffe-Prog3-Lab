package experia.coffee.experiacoffee.model;

import experia.coffee.experiacoffee.model.BuilderPattern.Utente;

public class LoginResult {
    private final boolean success;
    private final Utente user;

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
