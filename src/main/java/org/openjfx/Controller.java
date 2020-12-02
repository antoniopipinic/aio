package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event){
        if(!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()){
            validLogin();
        }else {
            loginMessageLabel.setText("Please enter username and password!");
        }

    }

    private void validLogin() {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("You're logged in successfully.");



                    //After Login going to main Page
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    //Center Stage on screen - width 866 height 606 manually added
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - 866) / 2);
                    stage.setY((screenBounds.getHeight() - 606) / 2);
                    //Show Scene
                    Parent mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
                    stage.setScene(new Scene(mainWindow));

                }else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
