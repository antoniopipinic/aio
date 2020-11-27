package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController{
    ObservableList<String> names = FXCollections.observableArrayList();
    @FXML
    private ListView<String> bookListView = new ListView<String>();
    @FXML
    protected void initialize(){
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String sqlString = "SELECT * FROM books";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlString);

            while(queryResult.next()){
                names.add(queryResult.getString(2));
            }

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        bookListView.setItems(names);
    System.out.println("test");
    }
}
