package experia.coffee.experiacoffee.controller;

import experia.coffee.experiacoffee.common.Constants;
import experia.coffee.experiacoffee.data.TicketQuery;
import experia.coffee.experiacoffee.model.BuilderPattern.Utente;
import experia.coffee.experiacoffee.model.SceneSwitch;
import experia.coffee.experiacoffee.model.SingletonPattern.UtenteSingleton;
import experia.coffee.experiacoffee.model.StatePattern.TicketingStatus.*;
import experia.coffee.experiacoffee.model.Ticket;
import experia.coffee.experiacoffee.utils.PopupWindow;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import experia.coffee.experiacoffee.utils.StatusImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DipendenteTicketingPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtenteSingleton singleton = UtenteSingleton.getInstance();
        Utente utente = singleton.getUtente();

        this.showTicketList();

        if (utente != null) {
            currentEmployee = utente.getEMAIL();
        }

         //MODIFICARE CON COLONNA STATO TICKET, AGGIUNGERE STATE PATTERN PER TICKET
        ticketTableStatus.setCellFactory(column -> {
            return new TextFieldTableCell<Ticket, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    // Verifica se la riga corrente ha dati
                    if (!isEmpty() && getItem() != null) {
                        StatusImpl impl = new StatusImpl();
                        TicketingStatus ticketState = impl.getTicketingStatus(item);
                        ticketState.applyStateStyle(this);
                    } else {
                        setStyle("");
                    }
                }
            };
        });
    }

    private String currentEmployee;

    @FXML
    private AnchorPane dipendenteTicketingPageAnchor;

    @FXML
    public TableView<Ticket> ticketList = new TableView<>();

    @FXML
    public TableColumn<Ticket, Integer> ticketTableID = new TableColumn<>("ID");

    @FXML
    public TableColumn<Ticket, String> ticketTableTitle = new TableColumn<>("TITOLO");

    @FXML
    public TableColumn<Ticket, String> ticketTableDescription = new TableColumn<>("DESCRIZIONE");

    @FXML
    public TableColumn<Ticket, String> ticketTableManagedBy = new TableColumn<>("GESTITO DA");

    @FXML
    public TableColumn<Ticket, String> ticketTableCreatedBy = new TableColumn<>("CREATO DA");

    @FXML
    public TableColumn<Ticket, String> ticketTableStatus = new TableColumn<>("STATO");

    @FXML
    public Button homePageButton, logoutButton, profilePageButton, updateTicketButton;

    @FXML
    public ToggleButton ticketEscapedToggle, ticketSuspendedToggle, ticketWorkingToggle, ticketNotHandledToggle;

    @FXML
    public Label ticketStatusSelected, ticketSelectedDescription, ticketSelectedTitle, ticketSelectedCreatedBy;

    public Integer ticketSelectedId;
    public String ticketSelectedStatus;

    @FXML
    public void onLogout() throws IOException {
        PopupWindow.showAlert(Alert.AlertType.INFORMATION, Constants.LOGOUT_SUCCESS, Constants.LOGOUT);
        new SceneSwitch(dipendenteTicketingPageAnchor, "loginPage.fxml");
    }

    @FXML
    public void goToHomePage() throws IOException {
        new SceneSwitch(dipendenteTicketingPageAnchor, "dipendenteHomePage.fxml");
    }

    @FXML
    public void goToProfilePage() throws IOException {
        new SceneSwitch(dipendenteTicketingPageAnchor, "profilePage.fxml");
    }

    @FXML
    public void updateTicketStatusById() throws IOException {
        Integer ticketId = ticketSelectedId;
        String ticketStatus = ticketSelectedStatus;

        System.out.println(ticketId);
        System.out.println(ticketStatus);

        if(ticketId != null && !ticketStatus.isEmpty()) {
            new PopupWindow(Alert.AlertType.INFORMATION, Constants.TICKET_UPDATE_IN_PROGRESS, Constants.TICKET).show();
            experia.coffee.experiacoffee.data.TicketQuery query = new TicketQuery();
            Integer queryResponse = query.updateTicketStatusById(ticketId, ticketStatus);
            if(queryResponse > 0) {
                new PopupWindow(Alert.AlertType.INFORMATION, Constants.TICKET_UPDATE_SUCCESS, Constants.TICKET).show();
                resetSummary();
                showTicketList();
            } else {
                new PopupWindowError(Alert.AlertType.ERROR, Constants.TICKET_UPDATE_ERROR, Constants.TICKET).show();
            }
        } else {
            new PopupWindowError(Alert.AlertType.ERROR, Constants.TICKET_UPDATE_EMPTY_STATUS_OR_ID, Constants.TICKET).show();
        }
    }

    @FXML
    public void showTicketList() {
        experia.coffee.experiacoffee.data.TicketQuery query = new experia.coffee.experiacoffee.data.TicketQuery();
        ObservableList<Ticket> list = query.getTicketList();
        ticketTableID.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("ID"));
        ticketTableTitle.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Title"));
        ticketTableDescription.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Description"));
        ticketTableManagedBy.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ManagedBy"));
        ticketTableCreatedBy.setCellValueFactory(new PropertyValueFactory<Ticket, String>("CreatedBy"));
        ticketTableStatus.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Status"));


        ticketList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ticket selectedTicket = ticketList.getSelectionModel().getSelectedItem();
                Integer idSelected = selectedTicket.getID();

                // permetti di assegnare ticket solo se non sono gestiti
                if (selectedTicket.getStatus().equals(Constants.TICKET_STATUS_NOT_HANDLED)) {
                    assignTicketToEmployee(currentEmployee, idSelected);
                }
            } else if (event.getClickCount() == 1) {
                Ticket selectedTicket = ticketList.getSelectionModel().getSelectedItem();
                ticketSelectedId = selectedTicket.getID();
                ticketSelectedTitle.setText(Constants.TICKET_SELECTED + ": " + selectedTicket.getTitle());
                ticketSelectedCreatedBy.setText(Constants.TICKET_CREATED_BY + ": " + selectedTicket.getCreatedBy());
                ticketSelectedDescription.setText(Constants.TICKET_DESCRIPTION + ": " + selectedTicket.getDescription());
                ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": " + selectedTicket.getStatus());
            }
        });

        ticketList.setItems(list);
    }

    public void assignTicketToEmployee(String emailEmployee, Integer idTicket) {
        experia.coffee.experiacoffee.data.TicketQuery query = new TicketQuery();

        Integer assignSuccessfull = query.assignTicket(emailEmployee, idTicket);
        if(assignSuccessfull > 0) {
            showTicketList();
        } else {
            new PopupWindowError(Alert.AlertType.ERROR, Constants.TABLE_GENERIC_ERROR, Constants.TICKET);
        }
    }

    @FXML
    private String handleToggleButtonAction() {
        if(ticketSuspendedToggle.isSelected()) {
            ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.TICKET_STATUS_SUSPENDED);
            ticketSelectedStatus = Constants.TICKET_STATUS_SUSPENDED;
        } else if (ticketEscapedToggle.isSelected()) {
            ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.TICKET_STATUS_ESCAPED);
            ticketSelectedStatus = Constants.TICKET_STATUS_ESCAPED;
        } else if (ticketWorkingToggle.isSelected()) {
            ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.TICKET_STATUS_WORKING);
            ticketSelectedStatus = Constants.TICKET_STATUS_WORKING;
        } else if (ticketNotHandledToggle.isSelected()) {
            ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": " + Constants.TICKET_STATUS_NOT_HANDLED);
            ticketSelectedStatus = Constants.TICKET_STATUS_NOT_HANDLED;
        } else {
            ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": ");
        }
        return null;
    }

    /*
    private TicketingStatus getStateInstance(String ticketStatus) {
        switch (ticketStatus) {
            case "Non gestito":
                return new NotHandledState();
            case "Preso in carico":
                return new TakenChargeStatus();
            case "In lavorazione":
                return new WorkingStatus();
            case "In sospensione":
                return new SuspendedStatus();
            case "Evaso":
                return new EscapedStatus();
            default:
                return new NotHandledState();
        }
    }
    */

    private void resetSummary() {
        ticketStatusSelected.setText(Constants.STATUS_SELECTED + ": ");
        ticketSelectedDescription.setText(Constants.TICKET_DESCRIPTION + ": ");
        ticketSelectedTitle.setText(Constants.TICKET_TITLE + ": ");
        ticketSelectedCreatedBy.setText(Constants.TICKET_CREATED_BY + ": ");
    }


}
