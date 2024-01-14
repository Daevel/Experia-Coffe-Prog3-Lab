package experia.coffee.experiacoffee.model;

public class LoginResult {
    private boolean success;
    private String ruolo;

    public LoginResult(boolean success, String ruolo) {
        this.success = success;
        this.ruolo = ruolo;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRuolo() {
        return ruolo;
    }
}
