package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class RecoverPasswordController implements Initializable {

    private String email_to;
    private String user_to;
    private static String styleTheme;

    @FXML
    private FlowPane flowPaneRoot;

    @FXML
    private Label labelEmail;

    @FXML
    private Button buttonCancel;

    @FXML
    private ImageView imageViewProfilePicture;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (styleTheme == null) {
            styleTheme = "resources/css/styles-dark.css";
        }
        flowPaneRoot.getStylesheets().add(styleTheme);

        Rectangle rectangle = new Rectangle(100, 120);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        imageViewProfilePicture.setClip(rectangle);

        user_to = LogInController.getUserdata()[0];
        email_to = LogInController.getUserdata()[1];
        labelEmail.setText(labelEmail.getText() + email_to);
    }

    @FXML
    void buttonCancelAction() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        LogInController.removeBlur();
        stage.close();
    }

    @FXML
    void buttonContinueAction() {

        Random random = new Random();
        int random_code = random.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "dennyschuyma@gmail.com";
        String password = "tukalukita_3000";
        String to = email_to;
        String subject = random_code + " es el código de recuperación de tu cuenta Computecnio";
        String message = "Hola, " + user_to + ":\n" +
                "Hemos recibido una solicitud para modificar la contraseña del sistema computecnio.\n" +
                "Introduce el siguiente código para restablecer la contraseña: " + random_code;
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Session session = Session.getDefaultInstance(properties, null);
        session.setDebug(false);

        Message messageClass = new MimeMessage(session);
        try {
            messageClass.setFrom(new InternetAddress(user));
            InternetAddress[] addresses = {new InternetAddress(to)};
            messageClass.setRecipients(Message.RecipientType.TO, addresses);
            messageClass.setSubject(subject);
            messageClass.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(messageClass, messageClass.getAllRecipients());
            transport.close();
            Dialog.successful("Código enviado", "Revise su corre electrónico asociado a esta cuenta hemos enviado un correo de recuperación", Dialog.ACCESS_GRANTED());
        } catch (MessagingException e) {
            Dialog.error("Error al enviar", "no se pudo enviar el código de recuperación", e.getMessage(), Dialog.ACCESS_DENIED());
        }
    }

    public static void setStyle(String style) {
        styleTheme = style;
    }
}