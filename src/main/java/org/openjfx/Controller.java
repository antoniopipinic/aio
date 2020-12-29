package org.openjfx;

import helper.DatenbankMG;
import helper.WindowMover;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.sql.ResultSet;

public class Controller {

    @FXML // FXML - Bezeichung für graphischen Output, @FXML definiert das diese Objekt im entsprechende fxml File ist
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;


    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }

    }

    private void validLogin() {

        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
        try {
            //Database management for login
            ResultSet queryResult = DatenbankMG.performQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("You're logged in successfully.");


                    //After Login going to main Page
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    //Center Stage on screen - width 866 height 606 manually added
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - 866) / 2);
                    stage.setY((screenBounds.getHeight() - 606) / 2);
                    Parent mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
                    Scene scene = new Scene(mainWindow);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);

                    WindowMover.loadResource(mainWindow);
                    WindowMover.loadStage(stage);
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
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
