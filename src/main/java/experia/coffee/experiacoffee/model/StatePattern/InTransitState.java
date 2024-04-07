package experia.coffee.experiacoffee.model.StatePattern;

import javafx.scene.Node;

public class InTransitState implements OrderState {

    @Override
    public void applyStateStyle(Node node) {
        node.setStyle("-fx-text-fill: yellow;");
    }
}
