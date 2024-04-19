package experia.coffee.experiacoffee.model.StatePattern.TicketingStatus;

import javafx.scene.Node;

public class TakenChargeStatus implements TicketingStatus {
    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: white; -fx-background-color: blue");
    }
}
