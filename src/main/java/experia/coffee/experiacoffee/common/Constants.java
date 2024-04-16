package experia.coffee.experiacoffee.common;

public class Constants {


    public static final String LOGIN = "Accesso";
    public static final String LOGIN_SUCCESS = "Accesso eseguito correttamente, redirect in corso...";
    public static final String LOGIN_ERROR = "Accesso negato, username o password incorretti.";
    public static final String LOGIN_ERROR_EMAIL = "Accesso negato, il formato della email inserita non è corretto.";

    public static final String SIGNUP = "Registrazione";
    public static final String SIGNUP_CORRECT = "Registrazione effettuata con successo, redirect in corso...";
    public static final String SIGNUP_ERROR = "Registrazione fallita. Esiste già un account con questa email, riprovare con un'altra email.";

    public static final String LOGOUT = "Logout";
    public static final String LOGOUT_SUCCESS = "Logout in corso, arrivederci.";

    public static final String PROFILE = "Profilo";
    public static final String PROFILE_PAGE_CHANGE_PASSWORD_SUCCESS = "Cambio password avvenuto con successo, redirect verso la pagina di login in corso...";
    public static final String PROFILE_PAGE_CHANGE_EMAIL_SUCCESS = "Cambio email avvenuto con successo, redirect verso la pagina di login in corso...";
    public static final String PROFILE_PAGE_CHANGE_EMAIL_ERROR = "Richiesta fallita. L'email inserità è già presente nei nostri sistemi, riprovare.";
    public static final String PASSWORD_MISMATCH_ERROR = "Le password inserite non coincidono, riprovare.";

}