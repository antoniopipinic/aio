package org.openjfx;

import helper.DatenbankMG;
import helper.PasswordSecurity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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

                String SQLfetch = null;
                try {
                    SQLfetch = "INSERT INTO users (email,password,fullname) VALUES ('" + emailTextField.getText() + "','" + PasswordSecurity.generateHash(passwordTextField1.getText(), PasswordSecurity.getAlgorithm()) + "','" + fullnameTextField.getText() + "')";
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                //String SQLfetch = "INSERT INTO users (email,password,fullname) VALUES ('" + emailTextField.getText() + "','" + passwordTextField1.getText() + "','" + fullnameTextField.getText()  + "')";

                DatenbankMG.performUpdate(SQLfetch);


                //Go back to main Page
                Stage stage = (Stage) registerCancelButton.getScene().getWindow();
                Parent mainWindow = null;
                try {
                    mainWindow = FXMLLoader.load(getClass().getResource("/login.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(mainWindow));

            }
        }

    };

    EventHandler<ActionEvent> cancelButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) registerCancelButton.getScene().getWindow();
            Parent mainWindow = null;
            try {
                mainWindow = FXMLLoader.load(getClass().getResource("/login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(mainWindow));
        }

    };
}