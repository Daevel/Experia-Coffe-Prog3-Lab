package experia.coffee.experiacoffee.model.SingletonPattern;

import experia.coffee.experiacoffee.model.BuilderPattern.Utente;

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
