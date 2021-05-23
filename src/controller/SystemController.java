package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.ChangeScene;

public class SystemController {
    @FXML
    private Button buttonBack;

    @FXML
    void backAction() {
        new ChangeScene(getClass().getResource("../view/LogIn.fxml"),buttonBack);
    }

    @FXML
    void settingAction() {
        new ChangeScene(getClass().getResource("../view/Setting.fxml"));
    }
}
