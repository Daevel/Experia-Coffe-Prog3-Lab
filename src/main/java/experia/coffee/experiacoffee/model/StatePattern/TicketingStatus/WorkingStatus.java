package experia.coffee.experiacoffee.model.StatePattern.TicketingStatus;

import javafx.scene.Node;

public class WorkingStatus implements TicketingStatus {
    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: black; -fx-background-color: yellow");
    }
}
