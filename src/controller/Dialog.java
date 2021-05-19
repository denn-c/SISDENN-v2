package controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dialog implements Initializable {

    private static String message;
    private static String details;
    private static String styleTheme;
    private static String styleTypeDialog;
    private static Boolean showDetailsButtonVisible;
    private static String IconTypeContent;
    private static String title;

    @FXML
    private FlowPane flowPaneRoot;

    @FXML
    private AnchorPane anchorPaneContainerOne;

    @FXML
    private VBox vBoxContainerTwo;

    @FXML
    private Label labelMessage;

    @FXML
    private TextArea textAreaDetails;

    @FXML
    private AnchorPane anchorPaneTextArea;

    @FXML
    private AnchorPane anchorPaneMargin;

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane showDetailsContainer;

    @FXML
    private ToggleButton showDetails;

    @FXML
    private SVGPath svgPathSowDetails;

    @FXML
    private SVGPath svgPathIcon;

    @FXML
    private Label labelTitle;

    @FXML
    public void closeAction() {
        LogInController.removeBlur();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void showDetailsAction() {
        Stage stage = (Stage) flowPaneRoot.getScene().getWindow();
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), svgPathSowDetails);
        if (showDetails.isSelected()) {
            stage.setHeight(330);
            flowPaneRoot.setPrefHeight(320);
            anchorPaneContainerOne.setPrefHeight(310);
            vBoxContainerTwo.setPrefHeight(310);
            anchorPaneMargin.setPrefHeight(140);
            anchorPaneTextArea.setPrefHeight(130);
            textAreaDetails.setPrefHeight(130);
            showDetails.setText("Ocultar detalles");
        } else {
            textAreaDetails.setPrefHeight(0);
            anchorPaneTextArea.setPrefHeight(0);
            anchorPaneMargin.setPrefHeight(0);
            vBoxContainerTwo.setPrefHeight(170);
            anchorPaneContainerOne.setPrefHeight(170);
            flowPaneRoot.setPrefHeight(200);
            stage.setHeight(200);
            showDetails.setText("Mostrar detalles");
        }
        rotateTransition.setByAngle(180);
        rotateTransition.play();

    }

    private static void loadDialog() {
        try {

            Stage primaryStage = (Stage) LogInController.flowPaneRootS.getScene().getWindow();
            double centerXPosition = (primaryStage.getX() + (primaryStage.getWidth()) / 2);
            double centerYPosition = (primaryStage.getY() + (primaryStage.getHeight()) / 2);

            URL location = Dialog.class.getResource("../view/Dialog.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(location));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setX(centerXPosition - 210);
            stage.setY(centerYPosition - 150);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String DATABASE_NOT_CONNECTED() {
        return "M10.5 0.1875C4.804688 0.1875 0.1875 2.34375 0.1875 5C0.1875 7.65625 4.804688 9.8125 10.5 9.8125C16.195313 9.8125 20.8125 7.65625 20.8125 5C20.8125 2.34375 16.195313 0.1875 10.5 0.1875 Z M 0.1875 6.28125L0.1875 10C0.1875 12.660156 4.804688 14.8125 10.5 14.8125C16.195313 14.8125 20.8125 12.660156 20.8125 10L20.8125 6.28125C20.8125 8.941406 16.195313 11.09375 10.5 11.09375C4.804688 11.09375 0.1875 8.941406 0.1875 6.28125 Z M 0.1875 11.28125L0.1875 15C0.1875 17.660156 4.804688 19.8125 10.5 19.8125C12.070313 19.8125 13.546875 19.636719 14.875 19.34375C14.609375 18.96875 14.46875 18.535156 14.46875 18.0625C14.46875 17.460938 14.703125 16.886719 15.125 16.46875L16.375 15.25C14.710938 15.789063 12.683594 16.09375 10.5 16.09375C4.804688 16.09375 0.1875 13.941406 0.1875 11.28125 Z M 20.8125 11.28125C20.8125 12.511719 19.816406 13.617188 18.1875 14.46875C18.742188 14.5 19.265625 14.734375 19.65625 15.125L20.5625 16.03125C20.71875 15.695313 20.8125 15.355469 20.8125 15 Z M 18.0625 16.0625C17.898438 16.0625 17.75 16.125 17.625 16.25L16.25 17.625C16 17.871094 16 18.25 16.25 18.5L18.75 21L16.25 23.5C16 23.746094 16 24.125 16.25 24.375L17.625 25.75C17.871094 26 18.25 26 18.5 25.75L21 23.25L23.5 25.75C23.746094 26 24.125 26 24.375 25.75L25.75 24.375C26 24.125 26 23.746094 25.75 23.5L23.25 21L25.75 18.5C26 18.253906 26 17.871094 25.75 17.625L24.375 16.25C24.125 16 23.746094 16 23.5 16.25L21 18.75L18.5 16.25C18.375 16.125 18.226563 16.0625 18.0625 16.0625 Z M 0.1875 16.28125L0.1875 20C0.1875 22.660156 4.804688 24.8125 10.5 24.8125C11.929688 24.8125 13.292969 24.683594 14.53125 24.4375C14.492188 24.277344 14.46875 24.105469 14.46875 23.9375C14.46875 23.335938 14.703125 22.761719 15.125 22.34375L16.46875 21L15.875 20.375C14.3125 20.820313 12.464844 21.09375 10.5 21.09375C4.804688 21.09375 0.1875 18.941406 0.1875 16.28125Z";
    }

    public static String ACCESS_DENIED() {
        return "M15 2.0019531C10.758 2.0019531 9 4.7229531 9 8.0019531C9 9.1059531 9.5273437 10.214844 9.5273438 10.214844C9.3153438 10.336844 8.9666875 10.724109 9.0546875 11.412109C9.2186875 12.695109 9.7749062 13.021828 10.128906 13.048828C10.263906 14.245828 11.55 15.777 12 16L12 18C11 21 3 19 3 26L14.523438 26C14.190437 25.06 14 24.054 14 23C14 19.461 16.047578 16.4085 19.017578 14.9375C19.426578 14.3675 19.801094 13.665828 19.871094 13.048828C20.225094 13.021828 20.781312 12.695109 20.945312 11.412109C21.033313 10.723109 20.684656 10.336844 20.472656 10.214844C20.472656 10.214844 21 9.2129531 21 8.0019531C21 5.5739531 20.047 3.5019531 18 3.5019531C18 3.5019531 17.289 2.0019531 15 2.0019531 z M 23 16C19.134 16 16 19.134 16 23C16 26.866 19.134 30 23 30C26.866 30 30 26.866 30 23C30 19.134 26.866 16 23 16 z M 21 20C21.25575 20 21.511531 20.097469 21.707031 20.292969L23 21.585938L24.292969 20.292969C24.683969 19.901969 25.316031 19.901969 25.707031 20.292969C26.098031 20.683969 26.098031 21.316031 25.707031 21.707031L24.414062 23L25.707031 24.292969C26.098031 24.683969 26.098031 25.316031 25.707031 25.707031C25.512031 25.902031 25.256 26 25 26C24.744 26 24.487969 25.902031 24.292969 25.707031L23 24.414062L21.707031 25.707031C21.512031 25.902031 21.256 26 21 26C20.744 26 20.487969 25.902031 20.292969 25.707031C19.901969 25.316031 19.901969 24.683969 20.292969 24.292969L21.585938 23L20.292969 21.707031C19.901969 21.316031 19.901969 20.683969 20.292969 20.292969C20.488469 20.097469 20.74425 20 21 20 z";
    }

    public static String ACCESS_GRANTED() {
        return "M15 3.0019531C10.758 3.0019531 9 5.7229531 9 9.0019531C9 10.105953 9.5273437 11.214844 9.5273438 11.214844C9.3153438 11.336844 8.9666875 11.724109 9.0546875 12.412109C9.2186875 13.695109 9.7749062 14.021828 10.128906 14.048828C10.263906 15.245828 11.55 16.777 12 17L12 19C11 22 3 20 3 27L14.947266 27C14.346266 25.794 14 24.439 14 23C14 19.186 16.376563 15.933047 19.726562 14.623047C19.795562 14.427047 19.850094 14.233828 19.871094 14.048828C20.225094 14.021828 20.781312 13.695109 20.945312 12.412109C21.033313 11.723109 20.684656 11.336844 20.472656 11.214844C20.472656 11.214844 21 10.212953 21 9.0019531C21 6.5739531 20.047 4.5019531 18 4.5019531C18 4.5019531 17.289 3.0019531 15 3.0019531 z M 23 16C19.134 16 16 19.134 16 23C16 26.866 19.134 30 23 30C26.866 30 30 26.866 30 23C30 19.134 26.866 16 23 16 z M 26 20C26.25575 20 26.511531 20.097469 26.707031 20.292969C27.098031 20.683969 27.098031 21.316031 26.707031 21.707031L22.707031 25.707031C22.512031 25.902031 22.256 26 22 26C21.744 26 21.487969 25.902031 21.292969 25.707031L19.292969 23.707031C18.901969 23.316031 18.901969 22.683969 19.292969 22.292969C19.683969 21.901969 20.316031 21.901969 20.707031 22.292969L22 23.585938L25.292969 20.292969C25.488469 20.097469 25.74425 20 26 20 z";
    }

    public static void error(String title_error, String message_error, String message_details, String icon) {

        showDetailsButtonVisible = true;
        LogInController.addBlur();
        styleTypeDialog = "resources/css/styles-error.css";
        IconTypeContent = icon;
        title = title_error;
        message = message_error;
        details = message_details;
        loadDialog();
    }

    public static void successful(String title_error, String message_successful, String icon) {
        showDetailsButtonVisible = false;
        LogInController.addBlur();
        title = title_error;
        styleTypeDialog = "resources/css/styles-successful.css";
        IconTypeContent = icon;
        message = message_successful;
        loadDialog();
    }

    public static void setStyle(String styles) {
        styleTheme = styles;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelMessage.setText(message);
        textAreaDetails.setText(details);
        if (styleTheme == null) {
            styleTheme = "resources/css/styles-dark.css";
        }
        flowPaneRoot.getStylesheets().add(styleTheme);
        flowPaneRoot.getStylesheets().add(styleTypeDialog);
        showDetailsContainer.setVisible(showDetailsButtonVisible);
        svgPathIcon.setContent(IconTypeContent);
        labelTitle.setText(title);
    }
}
