package experia.coffee.experiacoffee.model.StatePattern.TicketingStatus;

import javafx.scene.Node;

public class NotHandledState implements TicketingStatus{
    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: black;-fx-background-color: gray");
    }
}
