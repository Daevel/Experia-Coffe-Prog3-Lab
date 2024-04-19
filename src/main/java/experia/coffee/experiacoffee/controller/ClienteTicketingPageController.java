package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;

public class ClienteTicketingPageController implements Initializable {

    private String currentUserEmail;
    private final Date ticketCreationTimestamp = new Date();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        if(utente!= null) {
           currentUserEmail = utente.getEMAIL();
        }
    }

    @FXML
    AnchorPane clienteTicketingAnchorPane;

    @FXML
    Button sendTicketRequest, goBack;

    @FXML
    TextField ticketTitle;

    @FXML
    TextArea ticketDescription;

    @FXML
    private void goToHomePage() throws IOException {
        new SceneSwitch(clienteTicketingAnchorPane, "clienteHomePage.fxml");
    }

    @FXML
    private void onSendTicket() throws IOException {
        experia.coffee.experiacoffee.data.TicketQuery query = new experia.coffee.experiacoffee.data.TicketQuery();
        Boolean result = query.createTicket(ticketTitle.getText(), ticketDescription.getText(), currentUserEmail, ticketCreationTimestamp);


        if(result) {
            PopupWindow.showAlert(Alert.AlertType.INFORMATION, Constants.TICKET_SEND_SUCCESS, Constants.TICKET);
            new SceneSwitch(clienteTicketingAnchorPane, "clienteHomePage.fxml");
        } else {
            PopupWindowError.showErrorAlert(Alert.AlertType.ERROR, Constants.TICKET_SEND_ERROR, Constants.TICKET);
        }
    }
}
