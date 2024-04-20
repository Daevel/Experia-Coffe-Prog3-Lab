package experia.coffee.experiacoffee.model.StatePattern.OrderStatus;

import javafx.scene.Node;

public class DefaultState implements OrderState {

    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-background-color: gray; -fx-text-fill: black");
    }
}
