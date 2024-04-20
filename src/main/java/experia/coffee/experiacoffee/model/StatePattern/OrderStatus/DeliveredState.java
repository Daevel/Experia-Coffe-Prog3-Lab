package experia.coffee.experiacoffee.model.StatePattern.OrderStatus;

import javafx.scene.Node;

public class DeliveredState implements OrderState {

    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: white; -fx-background-color: green");
    }
}
