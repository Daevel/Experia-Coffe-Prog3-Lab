package experia.coffee.experiacoffee.model.ObserverPattern;

public interface ProductObserver {
    public void update();

    public void setSubject(ProductSubject sub);
}
