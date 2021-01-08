package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.ResultSet;

public class LoginPageController {

    @FXML // FXML - Bezeichung für graphischen Output, @FXML definiert das diese Objekt im entsprechende fxml File ist
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button addButton;
    @FXML
    private ImageView offIMG;

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }

    }


    private void validLogin() {


        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
        Data.setUsername(usernameTextField.getText());
        try {
            //Database management for login
            ResultSet queryResult = DatenbankMG.performQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("You're logged in successfully.");
                    //Go to main Page
                    MainApp.easyScene.showScene("/main.fxml");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void cancelButtonOnAction(ActionEvent event) {
        //close Stage
        MainApp.easyScene.off();
    }

    public void registerButtonOnAction(ActionEvent event) {
        MainApp.easyScene.showScene("/registerPage.fxml");
    }

    public void offClickedEvent(MouseEvent mouseEvent) {
        //close Stage
        MainApp.easyScene.off();
    }




    }

