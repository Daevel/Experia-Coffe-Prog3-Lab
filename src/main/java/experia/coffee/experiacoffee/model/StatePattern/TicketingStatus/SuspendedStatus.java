package experia.coffee.experiacoffee.model.StatePattern.TicketingStatus;

import javafx.scene.Node;

public class SuspendedStatus implements TicketingStatus {
    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: white; -fx-background-color: red");
    }
}
