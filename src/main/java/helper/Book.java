package helper;

public class Book {
    private String title = null;
    private String author = null;
    private String genre = null;
    private String isbn = null;
    private String owner = null;
    private String isRead = null;

    public Book(String title, String author, String genre, String isbn, boolean isRead, String owner) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.owner = owner;
        setIsRead(isRead);
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIsRead() {return isRead; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIsRead(boolean isRead) { this.isRead = isRead == true ? "verf\u00fcgbar" : "nicht verf\u00fcgbar"; }
}
