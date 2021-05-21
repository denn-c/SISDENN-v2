package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

public class RecoverPasswordController {

    @FXML
    private FlowPane flowPaneRadioButton;


    @FXML
    private RadioButton radioButtonEmail;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton radioButtonPhone;

    @FXML
    void radioButtonAction(ActionEvent event) {
        if (radioButtonEmail.isSelected()){
            flowPaneRadioButton.getStylesheets().add("resources/css/styles-radio-button.css");
        }else {
            flowPaneRadioButton.getStylesheets().clear();
        }
    }

}
