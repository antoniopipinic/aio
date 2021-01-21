package org.openjfx;

import helper.DatenbankMG;
import helper.PasswordSecurity;
import helper.components.WarningLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;


public class RegisterPageController {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField1;
    @FXML
    private PasswordField passwordTextField2;
    @FXML
    private TextField fullnameTextField;
    @FXML
    private Button registerLoginButton;
    @FXML
    private Button registerCancelButton;
    @FXML
    private WarningLabel errorRegisterText;

    @FXML
    protected void initialize() {
        registerLoginButton.setOnAction(addButtonEvent);
        registerCancelButton.setOnAction(cancelButtonEvent);
    }

    EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (emailTextField.getText().isBlank() || passwordTextField1.getText().isBlank() || passwordTextField2.getText().isBlank() || fullnameTextField.getText().isBlank()) {
                errorRegisterText.setText("Bitte alle Felder ausf\u00fcllen!");
                errorRegisterText.showError();
            }
            else if(passwordTextField1.getText().equals(passwordTextField2.getText())){

                String SQLfetch = null;
                try {
                    SQLfetch = "INSERT INTO users (email,password,fullname) VALUES ('" + emailTextField.getText() + "','" + PasswordSecurity.generateHash(passwordTextField1.getText(), PasswordSecurity.getAlgorithm()) + "','" + fullnameTextField.getText() + "')";
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                DatenbankMG.performUpdate(SQLfetch);
                //Go back to main Page
                MainApp.easyScene.showScene("/login.fxml");
            }else {
                errorRegisterText.setText("Die Passwörter müssen übereinstimmen!");
                errorRegisterText.showError();
            }
        }
    };

    EventHandler<ActionEvent> cancelButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Go back to main Page
            MainApp.easyScene.showScene("/login.fxml");
        }
    };
}