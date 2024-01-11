package experia.coffee.experiacoffee.model;

public class Utente {
    private String EMAIL;
    private String PASSWORD;

    public Utente(String EMAIL, String PASSWORD) {
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public String getEmail() {
        return EMAIL;
    }

    public String getPassword() {
        return PASSWORD;
    }

}
