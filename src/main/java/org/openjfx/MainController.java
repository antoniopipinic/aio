package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController{
    ObservableList<String> names = FXCollections.observableArrayList();
    @FXML
    private ListView<String> bookListView = new ListView<String>();
    @FXML
    private Button addButton;

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

        bookListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Close DB connection
                    try {
                        connectDB.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    //Use ListView's getSelected Item
                    //System.out.println(bookListView.getSelectionModel().getSelectedItem());
                    Data.setDataString(bookListView.getSelectionModel().getSelectedItem());

                    Stage stage = (Stage) bookListView.getScene().getWindow();
                    Parent detailPage = null;
                    try {
                        detailPage = FXMLLoader.load(getClass().getResource("/detailPage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(new Scene(detailPage));
                }
            }
        });

        addButton.setOnAction(addButtonEvent);
    }
EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Stage stage = (Stage) bookListView.getScene().getWindow();
        Parent detailPage = null;
        try {
            detailPage = FXMLLoader.load(getClass().getResource("/addPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(detailPage));
    }
};

}
