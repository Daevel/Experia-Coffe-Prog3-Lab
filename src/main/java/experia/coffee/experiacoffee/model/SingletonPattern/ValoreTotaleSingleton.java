package experia.coffee.experiacoffee.model.SingletonPattern;

public class ValoreTotaleSingleton {
    private static ValoreTotaleSingleton instance;
    private float valoreTotale;

    private ValoreTotaleSingleton() {
        // Costruttore privato per impedire l'istanziazione diretta
    }

    public static ValoreTotaleSingleton getInstance() {
        if (instance == null) {
            instance = new ValoreTotaleSingleton();
        }
        return instance;
    }

    public float getValoreTotale() {
        return valoreTotale;
    }

    public void setValoreTotale(float valoreTotale) {
        this.valoreTotale = valoreTotale;
    }
}
