package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import model.EncryptPassword;
import model.ShowPassword;
import model.SignIn;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    private static String styleTheme;
    private static FlowPane flowPaneRootS;

    @FXML
    private FlowPane flowPaneRoot;

    @FXML
    private TextField textFieldNamesAndSurnames;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldUserName;

    @FXML
    private TextField textFieldPassword1;

    @FXML
    private PasswordField passwordFieldPassword1;

    @FXML
    private TextField textFieldPassword2;

    @FXML
    private PasswordField passwordFieldPassword2;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private ToggleButton toggleButtonShowPassword1;

    @FXML
    private ToggleButton toggleButtonShowPassword2;

    @FXML
    private ComboBox<String> comboBoxUserType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signIn = new SignIn();
        comboBoxUserType.setItems(signIn.loadUserType());
        if (styleTheme == null) {
            styleTheme = "resources/css/styles-dark.css";
        }
        flowPaneRoot.getStylesheets().add(styleTheme);
        flowPaneRootS = flowPaneRoot;
    }

    @FXML
    void showPasswordAction(ActionEvent event) {
        if (event.getSource().equals(toggleButtonShowPassword1)) {
            ShowPassword showPassword = new ShowPassword(passwordFieldPassword1, textFieldPassword1, toggleButtonShowPassword1);
            showPassword.show();
        } else if (event.getSource().equals(toggleButtonShowPassword2)) {
            ShowPassword showPassword = new ShowPassword(passwordFieldPassword2, textFieldPassword2, toggleButtonShowPassword2);
            showPassword.show();
        }
    }

    @FXML
    void syncUpPassword(KeyEvent event) {
        if (event.getSource().equals(passwordFieldPassword1) || event.getSource().equals(textFieldPassword1)) {
            ShowPassword showPassword = new ShowPassword(passwordFieldPassword1, textFieldPassword1, event);
            showPassword.syncUpPassword();
        } else if (event.getSource().equals(passwordFieldPassword2) || event.getSource().equals(textFieldPassword2)) {
            ShowPassword showPassword = new ShowPassword(passwordFieldPassword2, textFieldPassword2, event);
            showPassword.syncUpPassword();
        }
    }

    @FXML
    void signInAction() {

        SignIn signIn = new SignIn();
        Users users = new Users();

        String password = passwordFieldPassword1.getText();
        String encrypt_password = EncryptPassword.sha1(password);

        users.setUserType(1);
        users.setNameLastName(textFieldNamesAndSurnames.getText());
        users.setEmail(textFieldEmail.getText());
        users.setUserName(textFieldUserName.getText());
        users.setPassword(encrypt_password);
        users.setPhone(Integer.parseInt(textFieldPhone.getText()));

        if (signIn.setUser(users)) {
            Dialog.successful("Registro Exitoso", "Su registro re realizo de forma exitosa, ya puedes iniciar sesión", Dialog.ACCESS_GRANTED());
        }
    }

    public static void addBlur() {
        GaussianBlur blurEffect = new GaussianBlur(20);
        if (flowPaneRootS !=null){
            flowPaneRootS.setEffect(blurEffect);
        }

    }

    public static void removeBlur() {
        if (flowPaneRootS !=null){
            flowPaneRootS.setEffect(null);
        }
    }

    public static void setStyle(String style) {
        styleTheme = style;
    }
}
