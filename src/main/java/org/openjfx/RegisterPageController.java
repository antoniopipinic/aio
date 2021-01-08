package org.openjfx;

import helper.DatenbankMG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


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
    private Text errorRegisterText;
    @FXML
    private Text successfulRegisterText;

    @FXML
    protected void initialize() {
        registerLoginButton.setOnAction(addButtonEvent);
        registerCancelButton.setOnAction(cancelButtonEvent);
    }

    EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            if (emailTextField.getText().isBlank() || passwordTextField1.getText().isBlank() || passwordTextField2.getText().isBlank() || fullnameTextField.getText().isBlank()) {
                errorRegisterText.setVisible(true);
                errorRegisterText.setText("Bitte alle Felder ausf\u00fcllen!");
            }
            else if(passwordTextField1.getText().equals(passwordTextField2.getText())){
                errorRegisterText.setVisible(true);
                errorRegisterText.setText("Die Passwörter müssen übereinstimmen!");

                String SQLfetch = "INSERT INTO users (email,password,fullname) VALUES ('" + emailTextField.getText() + "','" + passwordTextField1.getText() + "','" + fullnameTextField.getText()  + "')";

                DatenbankMG.performUpdate(SQLfetch);


                //Go back to main Page
                MainApp.easyScene.showScene("/main.fxml");

            }
        }

    };

    EventHandler<ActionEvent> cancelButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            MainApp.easyScene.showScene("/login.fxml");
        }

    };
}
