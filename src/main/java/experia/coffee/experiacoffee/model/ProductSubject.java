package experia.coffee.experiacoffee.model;

public interface ProductSubject {

    public void register(ProductObserver obj);
    public void unregister(ProductObserver obj);

    public void notifyObservers();

    public Object getUpdate(ProductObserver obj);

}
