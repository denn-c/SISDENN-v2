package controller;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import model.TransitionEffect;

public class LoginController {

    @FXML
    private Rectangle singUpFill;

    @FXML
    void transitionEntered() {
        new TransitionEffect(singUpFill, 1, 0);
    }

    @FXML
    void transitionExited() {
        new TransitionEffect(singUpFill, 0, 1);
    }
}
