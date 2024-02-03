package experia.coffee.experiacoffee.model.StatePattern;

import javafx.scene.Node;

public class PendingState implements OrderState {

    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-background-color: red;");
    }
}
