package org.openjfx;

import helper.Book;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    ObservableList<Book> books = FXCollections.<Book>observableArrayList();

    @FXML
    private TableView tableView = new TableView();
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
    private Button searchButton;


    @FXML
    protected void initialize() {
        TableColumn<Book, String> titleColumn = new TableColumn<>("Titel");


        TableColumn autorColumn = new TableColumn<>("Autor");


        TableColumn genreColumn = new TableColumn<>("Genre");


        TableColumn isbnColumn = new TableColumn<>("ISBN");


        tableView.getColumns().addAll(titleColumn, autorColumn, genreColumn, isbnColumn);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        warningText.setVisible(false);
        String sqlString = "SELECT * FROM books";

        //Getting all Books from DB
        try {
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);

            while (queryResult.next()) {
                Book book = new Book(queryResult.getString(2), queryResult.getString(3), queryResult.getString(4), queryResult.getString(5));
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        //Showing all books
        tableView.getItems().addAll(books);
        //Show Setailpage when clicking on a book
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    System.out.println(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
                    Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());

                    Stage stage = (Stage) tableView.getScene().getWindow();
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
        searchButton.setOnAction(searchButtonEvent);

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
    private final EventHandler<ActionEvent> searchButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {


            Stage stage = (Stage) tableView.getScene().getWindow();
            Parent detailPage = null;
            try {
                detailPage = FXMLLoader.load(getClass().getResource("/searchPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(detailPage));
        }


    };
    private final EventHandler<ActionEvent> editButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                //passing selected book to the editpage
                Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
            }

            Stage stage = (Stage) tableView.getScene().getWindow();
            Parent detailPage = null;
            try {
                detailPage = FXMLLoader.load(getClass().getResource("/editPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //warning when there is no book selected
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                warningText.setVisible(true);
            } else {

                stage.setScene(new Scene(detailPage));
            }

        }
    };
    private final EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) tableView.getScene().getWindow();
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
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                //passing selected book to the editpage
                Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
            }
            Stage stage = (Stage) tableView.getScene().getWindow();
            Parent detailPage = null;
            try {
                detailPage = FXMLLoader.load(getClass().getResource("/deletePage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            //warning when there is no book selected
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                warningText.setVisible(true);
            } else {
                stage.setScene(new Scene(detailPage));
            }

        }
    };

}
