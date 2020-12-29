package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.ResultSet;

public class MainController {
    ObservableList<String> names = FXCollections.observableArrayList();
    @FXML
    private ListView<String> bookListView = new ListView<String>();
    @FXML
    private Button addButton;
    @FXML
    private ImageView offIMG;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label warningText;

    @FXML
    protected void initialize() {
        warningText.setVisible(false);
        String sqlString = "SELECT * FROM books";
        //Getting all Books from DB
        try {
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);

            while (queryResult.next()) {
                names.add(queryResult.getString(2));//
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        //Showing all books
        bookListView.setItems(names);
        //Show Setailpage when clicking on a book
        bookListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
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
        editButton.setOnAction(editButtonEvent);
        deleteButton.setOnAction(deleteButtonEvent);

        //Close Stage when clicking on off IMG
        offIMG.setOnMouseClicked(offClickedEvent);

    }

    private final EventHandler offClickedEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //Event that closes the stage
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    };
    private final EventHandler<ActionEvent> editButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //passing selected book to the editpage
            Data.setDataString(bookListView.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) bookListView.getScene().getWindow();
            Parent detailPage = null;
            try {
                detailPage = FXMLLoader.load(getClass().getResource("/editPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //warning when there is no book selected
            if (bookListView.getSelectionModel().getSelectedItem() == null){
                warningText.setVisible(true);
            }
            else {
                stage.setScene(new Scene(detailPage));
            }

        }
    };
    private final EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {
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
    private final EventHandler<ActionEvent> deleteButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //passing selected book to the deletepage
            Data.setDataString(bookListView.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) bookListView.getScene().getWindow();
            Parent detailPage = null;
            try {
                detailPage = FXMLLoader.load(getClass().getResource("/deletePage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            //warning when there is no book selected
            if (bookListView.getSelectionModel().getSelectedItem() == null){
                warningText.setVisible(true);
            }
            else {
                stage.setScene(new Scene(detailPage));
            }

        }
    };

}
