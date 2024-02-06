package experia.coffee.experiacoffee.model.ObserverPattern;

public interface ProductSubject {

    void register(ProductObserver obj);
    void unregister(ProductObserver obj);

    void notifyObservers();

    Object getUpdate(ProductObserver obj);

}
