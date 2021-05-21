package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    public static FlowPane flowPaneRootS;

    @FXML
    private FlowPane flowPaneRoot;

    @FXML
    private TextField textFieldUserName;

    @FXML
    private SVGPath svgPathErrorUser;

    @FXML
    private ToggleButton buttonShowPassword;

    @FXML
    private TextField textFieldPassword2;

    @FXML
    private PasswordField passwordFieldPassword1;

    @FXML
    private SVGPath svgPathErrorPassword;

    @FXML
    private Button buttonSignIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flowPaneRootS = flowPaneRoot;
    }

    @FXML
    void showPasswordAction() {
        ShowPassword showPassword = new ShowPassword(passwordFieldPassword1, textFieldPassword2, buttonShowPassword);
        showPassword.show();
    }

    @FXML
    void syncUpPassword(KeyEvent event) {
        ShowPassword showPassword = new ShowPassword(passwordFieldPassword1, textFieldPassword2, event);
        showPassword.syncUpPassword();
    }

    @FXML
    void keyPressedAction(KeyEvent event) {

        if (event.getSource().equals(textFieldUserName)) {
            if (svgPathErrorUser.isVisible()) {
                hideError(textFieldUserName, svgPathErrorUser);
            }
        } else if (event.getSource().equals(passwordFieldPassword1) || event.getSource().equals(textFieldPassword2)) {
            if (svgPathErrorPassword.isVisible()) {
                hideError(textFieldPassword2, svgPathErrorPassword);
                hideError(passwordFieldPassword1);
            }
        }
    }

    @FXML
    void logInAction() {
        LogIn login = new LogIn();
        Users users = new Users();

        String password = EncryptPassword.sha1(passwordFieldPassword1.getText());
        users.setUserName(textFieldUserName.getText());
        users.setPassword(password);
        if (textFieldUserName.getText().isEmpty()) {
            sowError(textFieldUserName, svgPathErrorUser);
        } else if (passwordFieldPassword1.getText().isEmpty()) {

            sowError(textFieldPassword2, svgPathErrorPassword);
            sowError(passwordFieldPassword1);
        } else {
            if (login.checkLogin(users) == 1) {
                Dialog.successful("Acceso concedido", "Su solicitud de acceso al sistema a sido aprobada", Dialog.ACCESS_GRANTED());
            } else if (login.checkLogin(users) == -1) {
                Dialog.error("Acceso denegado", "Los datos que ingresaste no son correctos, o usted no esta registrado", "Verifique que su nombre de usuario y contraseña sean  los correctors o usted no esta registrado", Dialog.ACCESS_DENIED());
            }
        }
    }

    @FXML
    void signInAction() {
        new ChangeScene(getClass().getResource("../view/SignIn.fxml"), buttonSignIn);
    }

    @FXML
    void forgotPasswordAction() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/RecoverPassword.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            addBlur();
            stage.show();
        } catch (IOException ignored) {

        }
    }

    private void sowError(TextField textField, SVGPath svgPath) {
        textField.getStylesheets().add("resources/css/styles-validate.css");
        textField.setPromptText("Por favor rellene este campo");
        textField.requestFocus();
        svgPath.setVisible(true);
    }

    private void sowError(PasswordField passwordField) {
        passwordField.getStylesheets().add("resources/css/styles-validate.css");
        passwordField.setPromptText("Por favor rellene este campo");
        passwordField.requestFocus();
    }

    private void hideError(TextField textField, SVGPath svgPath) {
        textField.getStylesheets().clear();
        textField.setPromptText("Usuario");
        svgPath.setVisible(false);
    }

    private void hideError(PasswordField passwordField) {
        passwordField.getStylesheets().clear();
        passwordField.setPromptText("Contraseña");
    }

    public static void addBlur() {
        GaussianBlur blurEffect = new GaussianBlur(20);
        flowPaneRootS.setEffect(blurEffect);
    }

    public static void removeBlur() {
        flowPaneRootS.setEffect(null);
    }

    public static void lightTheme() {
        flowPaneRootS.getStylesheets().clear();
        flowPaneRootS.getStylesheets().add("/resources/css/styles-light.css");
        flowPaneRootS.getStylesheets().add("/resources/css/styles-general.css");
    }

    public static void darkTheme() {
        flowPaneRootS.getStylesheets().clear();
        flowPaneRootS.getStylesheets().add("/resources/css/styles-dark.css");
        flowPaneRootS.getStylesheets().add("/resources/css/styles-general.css");
    }
}
