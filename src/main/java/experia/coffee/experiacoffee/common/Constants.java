package experia.coffee.experiacoffee.common;

public class Constants {


    public static final String LOGIN = "Accesso";
    public static final String LOGIN_SUCCESS = "Accesso eseguito correttamente, redirect in corso...";
    public static final String LOGIN_ERROR = "Accesso negato, username o password incorretti.";
    public static final String LOGIN_ERROR_EMAIL = "Accesso negato, il formato della email inserita non è corretto.";

    public static final String SIGNUP = "Registrazione";
    public static final String SIGNUP_CORRECT = "Registrazione effettuata con successo, redirect in corso...";
    public static final String SIGNUP_ERROR_EMAIL_ALREADY_EXIST = "Registrazione fallita. Esiste già un account con questa email, riprovare con un'altra email.";
    public static final String SIGNUP_ERROR_EMAIL_PATTERN_INVALID = "Registrazione fallita. L'email inserita non rispetta i criteri validi. Riprovare.";
    public static final String SIGNUP_ERROR_EMAIL_PASSWORD_MISMATCH = "Registrazioe fallita. Le password inserite non coincidono, riprovare.";
    public static final String SIGNUP_MISSING_ACCEPT_TERMS_AND_POLICY = "Registrazione fallita. E' necessario accettare i termini e le condizioni per proseguire.";

    public static final String LOGOUT = "Logout";
    public static final String LOGOUT_SUCCESS = "Logout in corso, arrivederci.";

    public static final String PROFILE = "Profilo";
    public static final String PROFILE_PAGE_CHANGE_PASSWORD_SUCCESS = "Cambio password avvenuto con successo, redirect verso la pagina di login in corso...";
    public static final String PROFILE_PAGE_CHANGE_EMAIL_SUCCESS = "Cambio email avvenuto con successo, redirect verso la pagina di login in corso...";
    public static final String PROFILE_PAGE_CHANGE_EMAIL_ERROR = "Richiesta fallita. L'email inserità è già presente nei nostri sistemi, riprovare.";
    public static final String PASSWORD_MISMATCH_ERROR = "Le password inserite non coincidono, riprovare.";

    public static final String TICKET_SEND_SUCCESS = "Ticket generato ed inviato correttamente, redirect in corso...";
    public static final String TICKET_SEND_ERROR = "Errore nella generazione del ticket, riprovare.";
    public static final String TICKET_UPDATE_SUCCESS = "Ticket aggiornato con successo, carico la tabella...";
    public static final String TICKET_UPDATE_ERROR = "Errore nell'aggiornamento del seguente ticket, riprovare.";
    public static final String TICKET_UPDATE_EMPTY_STATUS_OR_ID = "Errore nell'aggiornamento del ticket,Stato o ID non forniti. Riprovare";
    public static final String TICKET_UPDATE_IN_PROGRESS = "Aggiornamento dello stato del ticker in corso...";
    public static final String TICKET = "Assistenza Clienti";
    public static final String TABLE_GENERIC_ERROR = "Errore generico della tabella, riprovare";
    public static final String TICKET_STATUS_NOT_HANDLED = "Non gestito";
    public static final String TICKET_STATUS_ESCAPED = "Evaso";
    public static final String TICKET_STATUS_SUSPENDED = "In sospensione";
    public static final String TICKET_STATUS_TAKEN_CHARDE = "Preso in carico";
    public static final String TICKET_STATUS_WORKING = "In lavorazione";
    public static final String TICKET_SELECTED = "Ticket selezionato";
    public static final String TICKET_CREATED_BY = "Creato da";
    public static final String TICKET_DESCRIPTION = "Descrizione";
    public static final String TICKET_TITLE = "Titolo";
    public static final String STATUS_SELECTED = "Stato selezionato";

    public static final String ORDER = "Ordini";
    public static final String ORDER_STATUS_ON_THE_WAY = "In Transito";
    public static final String ORDER_STATUS_ON_WAITING = "In Attesa";
    public static final String ORDER_STATUS_ON_DELIVERED = "Consegnato";
    public static final String ORDER_UPDATED_SUCCESS = "Ordine aggiornato correttamente";
    public static final String ORDER_UPDATED_ERROR = "Aggiornamento dell'ordine fallito, riprovare.";

    public static final String CART_CREATE_SUCCESS = "Il carrello è stato creato correttamente";
    public static final String CART_CREATE_FAILED = "Errore nella creazione del carrello";
    public static final String CART = "Carrello acquisti";
}