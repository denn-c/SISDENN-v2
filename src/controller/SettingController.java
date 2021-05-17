package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingController {

    @FXML
    private Button back;

    @FXML
    private ToggleButton darkLight;

    @FXML
    void darkLightAction() {

        if (darkLight.isSelected()) {
            darkLight.setText("Modo Oscuro");
            LogInController.lightTheme();
            Dialog.setStyle("/resources/css/styles-light.css");


        } else {
            darkLight.setText("Modo Claro");
            LogInController.darkTheme();
            Dialog.setStyle("/resources/css/styles-dark.css");

        }
    }

    @FXML
    void backAction() {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
}
