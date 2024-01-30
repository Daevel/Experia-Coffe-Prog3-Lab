package experia.coffee.experiacoffee.model;

public class Magazzino {

    private String NOME_FILIALE;
    private String CODICE_MAGAZZINO;
    private String ID_PRODOTTO;

    private String NOME_PRODOTTO;

    private Integer QUANTITA_PRODOTTO;

    private String NOME_MAGAZZINO;

    public Magazzino(String CODICE_MAGAZZINO,String ID_PRODOTTO, Integer QUANTITA_PRODOTTO, String NOME_PRODOTTO, String NOME_MAGAZZINO) {
        this.setCODICE_MAGAZZINO(CODICE_MAGAZZINO);
        this.setID_PRODOTTO(ID_PRODOTTO);
        this.setQUANTITA_PRODOTTO(QUANTITA_PRODOTTO);
        this.setNOME_PRODOTTO(NOME_PRODOTTO);
        this.setNOME_MAGAZZINO(NOME_MAGAZZINO);
    }
    public Magazzino(String NOME_FILIALE, String ID_PRODOTTO, String NOME_PRODOTTO, Integer QUANTITA_PRODOTTO) {
        this.setNOME_FILIALE(NOME_FILIALE);
        this.setID_PRODOTTO(ID_PRODOTTO);
        this.setNOME_PRODOTTO(NOME_PRODOTTO);
        this.setQUANTITA_PRODOTTO(QUANTITA_PRODOTTO);
    }

    public String getCODICE_MAGAZZINO() {
        return CODICE_MAGAZZINO;
    }

    public void setCODICE_MAGAZZINO(String CODICE_MAGAZZINO) {
        this.CODICE_MAGAZZINO = CODICE_MAGAZZINO;
    }

    public String getID_PRODOTTO() {
        return ID_PRODOTTO;
    }

    public void setID_PRODOTTO(String ID_PRODOTTO) {
        this.ID_PRODOTTO = ID_PRODOTTO;
    }

    public String getNOME_PRODOTTO() {
        return NOME_PRODOTTO;
    }

    public void setNOME_PRODOTTO(String NOME_PRODOTTO) {
        this.NOME_PRODOTTO = NOME_PRODOTTO;
    }

    public Integer getQUANTITA_PRODOTTO() {
        return QUANTITA_PRODOTTO;
    }

    public void setQUANTITA_PRODOTTO(Integer QUANTITA_PRODOTTO) {
        this.QUANTITA_PRODOTTO = QUANTITA_PRODOTTO;
    }

    public String getNOME_MAGAZZINO() {
        return NOME_MAGAZZINO;
    }

    public void setNOME_MAGAZZINO(String NOME_MAGAZZINO) {
        this.NOME_MAGAZZINO = NOME_MAGAZZINO;
    }

    public String getNOME_FILIALE() {
        return NOME_FILIALE;
    }

    public void setNOME_FILIALE(String NOME_FILIALE) {
        this.NOME_FILIALE = NOME_FILIALE;
    }
}
