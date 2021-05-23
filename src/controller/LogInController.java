package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;
import model.*;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    public static FlowPane flowPaneRootS;
    private static String[] userdata = new String[2];
    private static String styleTheme;

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

        if (styleTheme == null) {
            styleTheme = "resources/css/styles-dark.css";
        }
        flowPaneRoot.getStylesheets().add(styleTheme);
        flowPaneRootS = flowPaneRoot;

        String[] suggestions = {"chuyma","maria"};
        TextFields.bindAutoCompletion(textFieldUserName,suggestions);

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
    void hideErrorKeyPressedAction(KeyEvent event) {

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
        if (textFieldUserName.getText().isEmpty()) {
            sowError(textFieldUserName, svgPathErrorUser);
        } else if (passwordFieldPassword1.getText().isEmpty()) {
            sowError(textFieldPassword2, svgPathErrorPassword);
            sowError(passwordFieldPassword1);
        } else {
            LogIn login = new LogIn();
            Users users = new Users();
            String password = EncryptPassword.sha1(passwordFieldPassword1.getText());
            users.setUserName(textFieldUserName.getText());
            users.setPassword(password);
            if (login.checkLogin(users) == 1) {
                new ChangeScene(getClass().getResource("../view/System.fxml"), buttonSignIn);
            } else if (login.checkLogin(users) == -1) {
                Dialog.error("Acceso denegado", "Los datos que ingresaste no son correctos, o usted no esta registrado", "Verifique que su nombre de usuario y contraseña sean  los correctors o usted no esta registrado", Dialog.ACCESS_DENIED());
            }
        }
    }

    @FXML
    void forgotPasswordAction() {

        if (textFieldUserName.getText().isEmpty()) {
            sowError(textFieldUserName, svgPathErrorUser);
        } else {
            Users users = new Users();
            users.setUserName(textFieldUserName.getText());
            RecoverPassword recoverPassword = new RecoverPassword();
            if (recoverPassword.checkUser(users) == 1) {
                userdata = recoverPassword.getUserdata();
                new ChangeScene(getClass().getResource("../view/RecoverPassword.fxml"));
                addBlur();

            } else if(recoverPassword.checkUser(users) == -1){
                Dialog.error("El usuario no existe", "El usuario que ingresaste no esta registrado en el sistema", "Verifique que el usuario que ingresaste se valido, caso contrario su cuenta de usuario fue eliminado por la gerencia ponte en contacto con el gerente", Dialog.ACCESS_DENIED());
            }
        }
    }

    @FXML
    void signInAction() {
        new ChangeScene(getClass().getResource("../view/SignIn.fxml"), buttonSignIn);
    }

    public static String[] getUserdata() {
        return userdata;
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

    public static void setStyle(String style) {
        styleTheme = style;
    }
}
