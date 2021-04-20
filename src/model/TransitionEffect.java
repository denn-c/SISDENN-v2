package model;

import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionEffect {
    public TransitionEffect(Rectangle rectangle, double from_value, double to_value) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800),rectangle);
        fadeTransition.setFromValue(from_value);
        fadeTransition.setToValue(to_value);
        fadeTransition.play();
    }
}
