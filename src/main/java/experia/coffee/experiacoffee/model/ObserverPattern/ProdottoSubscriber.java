package experia.coffee.experiacoffee.model.ObserverPattern;

public class ProdottoSubscriber implements ProductObserver {

    private final String name;
    private ProductSubject topic;

    public ProdottoSubscriber(String nm) {
        this.name = nm;
    }


    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else {
            System.out.println(name + ":: Consuming message::" + msg);
        }
    }

    @Override
    public void setSubject(ProductSubject sub) {
        this.topic = sub;
    }
}
