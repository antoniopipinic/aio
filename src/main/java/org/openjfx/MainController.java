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
    private TextField fastSearchField;

    @FXML
    protected void initialize() {
        TableColumn titleColumn = new TableColumn<>("Titel");
        TableColumn autorColumn = new TableColumn<>("Autor");
        TableColumn genreColumn = new TableColumn<>("Genre");
        TableColumn isbnColumn = new TableColumn<>("ISBN");

        TableColumn titleColumnPublic = new TableColumn<>("Titel");
        TableColumn autorColumnPublic = new TableColumn<>("Autor");
        TableColumn genreColumnPublic = new TableColumn<>("Genre");
        TableColumn isbnColumnPublic = new TableColumn<>("ISBN");
        TableColumn ownerColumnPublic = new TableColumn<>("Eigent\u00fcmer");

        //Adding Columns to both tableviews
        tableView.getColumns().addAll(titleColumn, autorColumn, genreColumn, isbnColumn);
        publicLibraryTableView.getColumns().addAll(titleColumnPublic, autorColumnPublic, genreColumnPublic, ownerColumnPublic);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        titleColumnPublic.setCellValueFactory(new PropertyValueFactory<>("title"));
        autorColumnPublic.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumnPublic.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumnPublic.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ownerColumnPublic.setCellValueFactory(new PropertyValueFactory<>("owner"));

        //Getting all private Books from DB
        try {
            String sqlString = "SELECT books.title, books.autor, books.genre, books.isbn, users.fullname FROM books INNER JOIN users ON books.fk_user_id=users.id where users.email = '" + Data.getUsername() + "'";
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);
            while (queryResult.next()) {
                Book book = new Book(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4),queryResult.getString(5));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        //Getting all public Books from DB
        try {
            String sqlString = "SELECT books.title, books.autor, books.genre, books.isbn, users.fullname FROM books INNER JOIN users ON books.fk_user_id=users.id";
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);
            while (queryResult.next()) {
                Book book = new Book(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4),queryResult.getString(5));
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

        // Private Search
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> filteredData = new FilteredList<>(books, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        fastSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                // If filter text is empty, display all books.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare title, genre, author with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (book.getGenre().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> sortedData = new SortedList<>(filteredData);


        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);


        //Public Search
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> publicFilteredData = new FilteredList<>(publicBooks, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        fastSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            publicFilteredData.setPredicate(book -> {
                // If filter text is empty, display all books.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare title, author, genre with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches title.
                } else if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches title.
                }
                else if (book.getGenre().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                    return true; // Filter matches Genre
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Book> publicSortedData = new SortedList<>(publicFilteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        publicSortedData.comparatorProperty().bind(publicLibraryTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        publicLibraryTableView.setItems(publicSortedData);


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
