package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    private static String styleTheme;

    @FXML
    private FlowPane flowPaneRoot;

    @FXML
    private ComboBox<String> comboBoxUserType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList <String> options = FXCollections.observableArrayList("Item 1","Item 2","Item 3");
        comboBoxUserType.setItems(options);
        if (styleTheme == null) {
            styleTheme = "resources/css/styles-dark.css";
        }
        flowPaneRoot.getStylesheets().add(styleTheme);

    }
    public static void setStyle(String style){
        styleTheme = style;
    }
}
