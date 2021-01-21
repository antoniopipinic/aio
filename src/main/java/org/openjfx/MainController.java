package org.openjfx;

import helper.Book;
import helper.Data;
import helper.DatenbankMG;
import helper.components.WarningLabel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class MainController {

    private ObservableList<Book> books = FXCollections.<Book>observableArrayList();
    private ObservableList<Book> publicBooks = FXCollections.<Book>observableArrayList();

    @FXML
    private TableView tableView = new TableView();
    @FXML
    private TableView publicLibraryTableView = new TableView();
    @FXML
    private Button addButton;
    @FXML
    private ImageView offIMG;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private WarningLabel warningLabel;
    @FXML
    private ImageView logOutIMG;
    @FXML
    private Text welcomeMessage;
    @FXML
    private TextField fastSearchField;

    @FXML
    protected void initialize() {
        TableColumn titleColumn = new TableColumn<>("Titel");
        TableColumn autorColumn = new TableColumn<>("Autor");
        TableColumn genreColumn = new TableColumn<>("Genre");
        TableColumn isbnColumn = new TableColumn<>("ISBN");
        TableColumn isReadColumn = new TableColumn<>("Verfügbarkeit");

        TableColumn titleColumnPublic = new TableColumn<>("Titel");
        TableColumn autorColumnPublic = new TableColumn<>("Autor");
        TableColumn genreColumnPublic = new TableColumn<>("Genre");
        TableColumn isbnColumnPublic = new TableColumn<>("ISBN");
        TableColumn isReadColumnPublic = new TableColumn<>("Verfügbarkeit");
        TableColumn ownerColumnPublic = new TableColumn<>("Eigentümer");

        //Adding Columns to both tableviews
        tableView.getColumns().addAll(titleColumn, autorColumn, genreColumn, isbnColumn, isReadColumn);
        publicLibraryTableView.getColumns().addAll(titleColumnPublic, autorColumnPublic, genreColumnPublic, isReadColumnPublic, ownerColumnPublic);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        isReadColumn.setCellValueFactory(new PropertyValueFactory<>("isRead"));

        titleColumnPublic.setCellValueFactory(new PropertyValueFactory<>("title"));
        autorColumnPublic.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumnPublic.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumnPublic.setCellValueFactory(new PropertyValueFactory<>("genre"));
        isReadColumnPublic.setCellValueFactory(new PropertyValueFactory<>("isRead"));
        ownerColumnPublic.setCellValueFactory(new PropertyValueFactory<>("owner"));

        welcomeMessage.setText("Willkommen, "+Data.getFullName());

        //Getting all private Books from DB
        try {
            String sqlString = "SELECT books.title, books.autor, books.genre, books.isbn, books.ausgeliehen, users.fullname FROM books INNER JOIN users ON books.fk_user_id=users.id where users.email = '" + Data.getUsername() + "'";
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);
            while (queryResult.next()) {
                Book book = new Book(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4),queryResult.getBoolean(5), queryResult.getString(6));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        //Getting all public Books from DB
        try {
            String sqlString = "SELECT books.title, books.autor, books.genre, books.isbn, books.ausgeliehen, users.fullname FROM books INNER JOIN users ON books.fk_user_id=users.id";
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);
            while (queryResult.next()) {
                Book book = new Book(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4),queryResult.getBoolean(5), queryResult.getString(6));
                publicBooks.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        //Showing all private books
        tableView.getItems().addAll(books);
        //Showing all public books
        publicLibraryTableView.getItems().addAll(publicBooks);
        //Show Detailpage when clicking on a book
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Save book title for the next Page
                    Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
                    MainApp.easyScene.showScene("/detailPage.fxml");
                }
            }
        });

        //Double click on a public Book -> Detail Page
        publicLibraryTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Save book title for the next Page
                    Data.setDataString(((Book) publicLibraryTableView.getSelectionModel().getSelectedItem()).getTitle());
                    MainApp.easyScene.showScene("/detailPage.fxml");
                }
            }
        });

        addButton.setOnAction(addButtonEvent);
        editButton.setOnAction(editButtonEvent);
        deleteButton.setOnAction(deleteButtonEvent);

        //Close Stage when clicking on off IMG
        offIMG.setOnMouseClicked(offClickedEvent);
        //LogOut Stage when clicking on logout img
        logOutIMG.setOnMouseClicked(logOutClickedEvent);
    }

    private final EventHandler logOutClickedEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            MainApp.easyScene.showScene("/login.fxml");
        }
    };

    private final EventHandler offClickedEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //Event that closes the stage
            MainApp.easyScene.off();
        }
    };

    private final EventHandler<ActionEvent> searchButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MainApp.easyScene.showScene("/searchPage.fxml");
        }
    };

    private final EventHandler<ActionEvent> editButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                //passing selected book to the editpage
                Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
            }
            //warning when there is no book selected
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                warningLabel.showError();
            } else {
                MainApp.easyScene.showScene("/editPage.fxml");
            }
        }
    };
    private final EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MainApp.easyScene.showScene("/addPage.fxml");
        }
    };
    private final EventHandler<ActionEvent> deleteButtonEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                //passing selected book to the editpage
                Data.setDataString(((Book) tableView.getSelectionModel().getSelectedItem()).getTitle());
            }
            //warning when there is no book selected
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                warningLabel.showError();
            } else {
                MainApp.easyScene.showScene("/deletePage.fxml");
            }
        }
    };
}
