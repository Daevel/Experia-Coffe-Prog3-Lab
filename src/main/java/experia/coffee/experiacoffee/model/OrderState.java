package experia.coffee.experiacoffee.model;

interface OrderState {
    void processOrder(Ordine ordine);
}

class ProcessingState implements OrderState {

    @Override
    public void processOrder(Ordine ordine) {
        ordine.setSTATO_ORDINE(this.toString());
    }
}

class ShippedState implements OrderState {

    @Override
    public void processOrder(Ordine ordine) {
        ordine.setSTATO_ORDINE(this.toString());
    }
}