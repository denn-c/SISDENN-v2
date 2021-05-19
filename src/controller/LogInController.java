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
import javafx.stage.Stage;
import model.ChangeScene;
import model.Login;
import model.ShowPassword;
import model.Users;

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
    private ToggleButton buttonShowPassword;

    @FXML
    private TextField textFieldPassword2;

    @FXML
    private PasswordField textFieldPassword1;

    @FXML
    private Button buttonSignIn;


    @FXML
    void singInAction() {
        Login login = new Login();
        Users users = new Users();

        users.setUserName(textFieldUserName.getText());
        users.setPassword(textFieldPassword1.getText());

        if (login.checkLogin(users)==1){

            Dialog.successful("Acceso concedido","Su solicitud de acceso al sistema",Dialog.ACCESS_GRANTED());
        }

        else if (login.checkLogin(users)== -1) {
            Dialog.error("Acceso denegado","Los datos que ingresaste no son correctos, o usted no esta registrado","Verifique que su nombre de usuario y contraseña sean  los correctors o usted no esta registrado",Dialog.ACCESS_DENIED());
        }
    }

    @FXML
    void showPasswordAction() {
        ShowPassword showPassword = new ShowPassword(textFieldPassword1, textFieldPassword2, buttonShowPassword);
        showPassword.show();
    }

    @FXML
    void syncUpPassword(KeyEvent event) {
        ShowPassword showPassword = new ShowPassword(textFieldPassword1, textFieldPassword2, event);
        showPassword.syncUpPassword();
    }

    @FXML
    void forgotPasswordAction() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Setting.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void signInAction() {
        new ChangeScene(getClass().getResource("../view/SignIn.fxml"),buttonSignIn);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flowPaneRootS = flowPaneRoot;
    }
}
