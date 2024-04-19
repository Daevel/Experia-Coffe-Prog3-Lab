package experia.coffee.experiacoffee.utils;

import experia.coffee.experiacoffee.model.StatePattern.OrderStatus.*;
import experia.coffee.experiacoffee.model.StatePattern.TicketingStatus.*;

public class StatusImpl {

    public OrderState getOrderStateInstance(String statoOrdine) {
        switch (statoOrdine) {
            case "Consegnato":
                return new DeliveredState();
            case "In Transito":
                return new InTransitState();
            case "In Attesa":
                return new PendingState();
            default:
                return new DefaultState();
        }
    }

    public TicketingStatus getTicketingStatus(String ticketStatus) {
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

}
