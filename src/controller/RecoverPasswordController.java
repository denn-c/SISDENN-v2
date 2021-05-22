package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecoverPasswordController implements Initializable {

    @FXML
    public ToggleGroup toggleGroup;

    @FXML
    private Button buttonCancel;

    @FXML
    private ImageView imageViewProfilePicture;

    @FXML
    private RadioButton radioButtonEmail;

    @FXML
    private RadioButton radioButtonPhone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Rectangle rectangle = new Rectangle(80, 100);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        imageViewProfilePicture.setClip(rectangle);
        String phone = LogInController.getEmailPhone()[1];
        phone = phone.substring(phone.length() - 2);
        radioButtonEmail.setText(radioButtonEmail.getText() +LogInController.getEmailPhone()[0]);
        radioButtonPhone.setText(radioButtonPhone.getText() + "*******" + phone);
    }

    @FXML
    void buttonCancelAction() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        LogInController.removeBlur();
        stage.close();
    }

    @FXML
    void radioButtonAction(ActionEvent event) {
        if (event.getSource().equals(radioButtonEmail)) {
            System.out.println("Por email");
        } else if (event.getSource().equals(radioButtonPhone)) {
            System.out.println("por mensaje");
        }
    }

    @FXML
    void buttonContinueAction() {

    }

}