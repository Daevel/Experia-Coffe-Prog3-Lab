package experia.coffee.experiacoffee.model.StatePattern.TicketingStatus;

import javafx.scene.Node;

public class EscapedStatus implements TicketingStatus {
    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: white; -fx-background-color: green");
    }
}
