package experia.coffee.experiacoffee.model;

public class UtenteSingleton {
    private static UtenteSingleton instance;
    private Utente utente;

    private UtenteSingleton() {

    }

    public static UtenteSingleton getInstance() {
        if (instance == null) {
            instance = new UtenteSingleton();
        }
        return  instance;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
