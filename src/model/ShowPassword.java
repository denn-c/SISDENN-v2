package model;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

public class ShowPassword {

    private final PasswordField password1;
    private final TextField password2;
    private ToggleButton showPassword;
    private KeyEvent event;

    public ShowPassword(PasswordField password1, TextField password2, ToggleButton showPassword) {
        this.password1 = password1;
        this.password2 = password2;
        this.showPassword = showPassword;
    }

    public ShowPassword(PasswordField password1, TextField password2, KeyEvent event) {
        this.password1 = password1;
        this.password2 = password2;
        this.event = event;
    }

    public void show() {
        if (showPassword.isSelected()) {
            password2.setText(password1.getText());
            password1.setVisible(false);
            password2.setVisible(true);
            password2.requestFocus();
            password2.selectEnd();
        } else {
            password1.setText(password2.getText());
            password2.setVisible(false);
            password1.setVisible(true);
            password1.requestFocus();
            password1.selectEnd();
        }
    }

    public void syncUpPassword() {
        if (event.getSource().equals(password1)) {
            password2.setText(password1.getText());
        } else if (event.getSource().equals(password2)) {
            password1.setText(password2.getText());
        }
    }
}
