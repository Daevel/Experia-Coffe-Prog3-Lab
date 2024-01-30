package experia.coffee.experiacoffee.model;

public interface ProductObserver {
    public void update();

    public void setSubject(ProductSubject sub);
}
