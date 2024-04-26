package experia.coffee.experiacoffee.model;

import experia.coffee.experiacoffee.model.StatePattern.OrderStatus.OrderState;
import javafx.scene.Node;

public class Ordine {
    private Integer ID;
    private Integer NUMERO_ORDINE;
    private String DESTINAZIONE;

    private String ORDINATO_DA;
    private String FILIALE_IN_CARICO;

    private String CORRIERE_IN_CARICO;
    private String STATO_ORDINE;

    private OrderState state;

    public Ordine(Integer ID, Integer NUMERO_ORDINE, String ORDINATO_DA, String DESTINAZIONE, String FILIALE_IN_CARICO, String CORRIERE_IN_CARICO, String STATO_ORDINE) {
        this.setID(ID);
       this.setNUMERO_ORDINE(NUMERO_ORDINE);
       this.setORDINATO_DA(ORDINATO_DA);
       this.setDESTINAZIONE(DESTINAZIONE);
       this.setFILIALE_IN_CARICO(FILIALE_IN_CARICO);
       this.setCORRIERE_IN_CARICO(CORRIERE_IN_CARICO);
       this.setSTATO_ORDINE(STATO_ORDINE);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getNUMERO_ORDINE() {
        return NUMERO_ORDINE;
    }

    public void setNUMERO_ORDINE(Integer NUMERO_ORDINE) {
        this.NUMERO_ORDINE = NUMERO_ORDINE;
    }

    public String getDESTINAZIONE() {
        return DESTINAZIONE;
    }

    public void setDESTINAZIONE(String DESTINAZIONE) {
        this.DESTINAZIONE = DESTINAZIONE;
    }

    public String getORDINATO_DA() {
        return ORDINATO_DA;
    }

    public void setORDINATO_DA(String ORDINATO_DA) {
        this.ORDINATO_DA = ORDINATO_DA;
    }

    public String getFILIALE_IN_CARICO() {
        return FILIALE_IN_CARICO;
    }

    public void setFILIALE_IN_CARICO(String FILIALE_IN_CARICO) {
        this.FILIALE_IN_CARICO = FILIALE_IN_CARICO;
    }

    public String getCORRIERE_IN_CARICO() {
        return CORRIERE_IN_CARICO;
    }

    public void setCORRIERE_IN_CARICO(String CORRIERE_IN_CARICO) {
        this.CORRIERE_IN_CARICO = CORRIERE_IN_CARICO;
    }

    public String getSTATO_ORDINE() {
        return STATO_ORDINE;
    }

    public void setSTATO_ORDINE(String STATO_ORDINE) {
        this.STATO_ORDINE = STATO_ORDINE;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void applyStateStyle(Node node) {
        if (state != null) {
            state.applyStateStyle(node);
        }
    }
}
