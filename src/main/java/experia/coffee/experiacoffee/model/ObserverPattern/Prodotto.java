package experia.coffee.experiacoffee.model.ObserverPattern;


import java.util.ArrayList;
import java.util.List;

public class Prodotto implements ProductSubject {
    private String NOME_PRODOTTO;
    private Float PREZZO_PRODOTTO;
    private Integer QUANTITA;
    private String PROVENIENZA;
    private String ID_FORNITURA;
    private String ID_PRODOTTO;

    public Prodotto(String NOME_PRODOTTO, Float PREZZO_PRODOTTO, Integer QUANTITA, String PROVENIENZA, String ID_FORNITURA, String ID_PRODOTTO) {
        this.setNOME_PRODOTTO(NOME_PRODOTTO);
        this.setPREZZO_PRODOTTO(PREZZO_PRODOTTO);
        this.setQUANTITA(QUANTITA);
        this.setPROVENIENZA(PROVENIENZA);
        this.setID_FORNITURA(ID_FORNITURA);
        this.setID_PRODOTTO(ID_PRODOTTO);
        this.observers = new ArrayList<>();
    }
    public Prodotto(String NOME_PRODOTTO, Float PREZZO_PRODOTTO, Integer QUANTITA, String PROVENIENZA, String ID_FORNITURA) {
        this.setNOME_PRODOTTO(NOME_PRODOTTO);
        this.setPREZZO_PRODOTTO(PREZZO_PRODOTTO);
        this.setQUANTITA(QUANTITA);
        this.setPROVENIENZA(PROVENIENZA);
        this.setID_FORNITURA(ID_FORNITURA);
    }

    public String getNOME_PRODOTTO() {
        return NOME_PRODOTTO;
    }

    public void setNOME_PRODOTTO(String NOME_PRODOTTO) {
        this.NOME_PRODOTTO = NOME_PRODOTTO;
    }

    public Float getPREZZO_PRODOTTO() {
        return PREZZO_PRODOTTO;
    }

    public void setPREZZO_PRODOTTO(Float PREZZO_PRODOTTO) {
        this.PREZZO_PRODOTTO = PREZZO_PRODOTTO;
    }

    public Integer getQUANTITA() {
        return QUANTITA;
    }

    public void setQUANTITA(Integer QUANTITA) {
        this.QUANTITA = QUANTITA;
    }

    public String getPROVENIENZA() {
        return PROVENIENZA;
    }

    public void setPROVENIENZA(String PROVENIENZA) {
        this.PROVENIENZA = PROVENIENZA;
    }

    public String getID_FORNITURA() {
        return ID_FORNITURA;
    }

    public void setID_FORNITURA(String ID_FORNITURA) {
        this.ID_FORNITURA = ID_FORNITURA;
    }

    public String getID_PRODOTTO() {
        return ID_PRODOTTO;
    }

    public void setID_PRODOTTO(String ID_PRODOTTO) {
        this.ID_PRODOTTO = ID_PRODOTTO;
    }

    private List<ProductObserver> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    @Override
    public void register(ProductObserver obj) {
        if(obj == null)
            throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj))
                observers.add(obj);
        }
    }

    @Override
    public void unregister(ProductObserver obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<ProductObserver> observerLocal = null;
        synchronized (MUTEX) {
            if(!changed)
                return;
            observerLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (ProductObserver obj: observerLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(ProductObserver obj) {
        return this.message;
    }


    public String postMessage(String msg) {
        System.out.println("Message Posted to Topic:"+msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
        return msg;
    }

}
