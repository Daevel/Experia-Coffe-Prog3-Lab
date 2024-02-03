package experia.coffee.experiacoffee.model.BuilderPattern;
public class Utente {

    // required
    private String EMAIL;
    private String PASSWORD;

    // optionals
    private String NAME;
    private String SURNAME;
    private String CELLULARE;
    private String CITTA;
    private String VIA;
    private String N_CIVICO;
    private String CAP;
    private String NUM_CARTA;
    private String CVV_CARTA;
    private String INTESTATARIO_CARTA;
    private String SCADENZA_CARTA;
    private String RUOLO;
    private String DATA_DI_NASCITA;
    private String CODICE_FISCALE;

    public String getEMAIL() {
        return EMAIL;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public String getCELLULARE() {
        return CELLULARE;
    }

    public String getCITTA() {
        return CITTA;
    }

    public String getVIA() {
        return VIA;
    }

    public String getN_CIVICO() {
        return N_CIVICO;
    }

    public String getCAP() {
        return CAP;
    }

    public String getNUM_CARTA() {
        return NUM_CARTA;
    }

    public String getCVV_CARTA() {
        return CVV_CARTA;
    }

    public String getINTESTATARIO_CARTA() {
        return INTESTATARIO_CARTA;
    }

    public String getSCADENZA_CARTA() {
        return SCADENZA_CARTA;
    }

    public String getDATA_DI_NASCITA() {
        return DATA_DI_NASCITA;
    }

    public String getCODICE_FISCALE() {
        return CODICE_FISCALE;
    }

    public String getRUOLO() {
        return RUOLO;
    }
    private Utente(UtenteBuilder builder) {
       this.NAME = builder.NAME;
       this.SURNAME = builder.SURNAME;
       this.EMAIL = builder.EMAIL;
       this.PASSWORD = builder.PASSWORD;
       this.CELLULARE = builder.CELLULARE;
       this.CITTA = builder.CITTA;
       this.VIA = builder.VIA;
       this.N_CIVICO = builder.N_CIVICO;
       this.CAP = builder.CAP;
       this.INTESTATARIO_CARTA = builder.INTESTATARIO_CARTA;
       this.NUM_CARTA = builder.NUM_CARTA;
       this.CVV_CARTA = builder.CVV_CARTA;
       this.SCADENZA_CARTA = builder.SCADENZA_CARTA;
       this.RUOLO = builder.RUOLO;
       this.CODICE_FISCALE = builder.CODICE_FISCALE;
       this.DATA_DI_NASCITA = builder.DATA_DI_NASCITA;
    }

    public static class UtenteBuilder {

        // required
        private String EMAIL;
        private String PASSWORD;

        // optionals

        private String NAME;
        private String SURNAME;
        private String CELLULARE;

        private String CITTA;
        private String VIA;
        private String N_CIVICO;
        private String CAP;

        private String NUM_CARTA;
        private String CVV_CARTA;
        private String INTESTATARIO_CARTA;
        private String SCADENZA_CARTA;
        private String RUOLO;

        private String CODICE_FISCALE;
        private String DATA_DI_NASCITA;



        public UtenteBuilder(String EMAIL, String PASSWORD) {
            this.EMAIL = EMAIL;
            this.PASSWORD = PASSWORD;
        }

        public UtenteBuilder setNAME(String NAME) {
            this.NAME = NAME;
            return this;
        }
        public UtenteBuilder setSURNAME(String SURNAME) {
            this.SURNAME = SURNAME;
            return this;
        }
        public UtenteBuilder setCELLULARE(String CELLULARE) {
            this.CELLULARE = CELLULARE;
            return this;
        }
        public UtenteBuilder setCITTA(String CITTA) {
            this.CITTA = CITTA;
            return this;
        }
        public UtenteBuilder setVIA(String VIA) {
            this.VIA = VIA;
            return this;
        }
        public UtenteBuilder setN_CIVICO(String N_CIVICO) {
            this.N_CIVICO = N_CIVICO;
            return this;
        }

        public UtenteBuilder setCAP(String CAP) {
            this.CAP = CAP;
            return this;
        }

        public UtenteBuilder setINTESTATARIO_CARTA(String INTESTATARIO_CARTA) {
            this.INTESTATARIO_CARTA = INTESTATARIO_CARTA;
            return this;
        }
        public UtenteBuilder setSCADENZA_CARTA(String SCADENZA_CARTA) {
            this.SCADENZA_CARTA = SCADENZA_CARTA;
            return this;
        }
        public UtenteBuilder setNUM_CARTA(String NUM_CARTA) {
            this.NUM_CARTA = NUM_CARTA;
            return this;
        }
        public UtenteBuilder setCVV_CARTA(String CVV_CARTA) {
            this.CVV_CARTA = CVV_CARTA;
            return this;
        }

        public UtenteBuilder setRUOLO(String RUOLO) {
            this.RUOLO = RUOLO;
            return this;
        }

        public UtenteBuilder setCODICE_FISCALE(String CODICE_FISCALE) {
            this.CODICE_FISCALE = CODICE_FISCALE;
            return this;
        }

        public UtenteBuilder setDATA_DI_NASCITA(String DATA_DI_NASCITA) {
            this.DATA_DI_NASCITA = DATA_DI_NASCITA;
            return this;
        }

        public Utente build() {
            return new Utente(this);
        }

    }

}
