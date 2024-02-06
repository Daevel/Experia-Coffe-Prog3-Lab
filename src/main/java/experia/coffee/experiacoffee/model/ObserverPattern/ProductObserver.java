package experia.coffee.experiacoffee.model.ObserverPattern;

public interface ProductObserver {
    void update();

    void setSubject(ProductSubject sub);
}
