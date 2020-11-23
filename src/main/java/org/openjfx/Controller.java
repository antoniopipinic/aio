package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    @FXML
    private Button cancelButton;
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
