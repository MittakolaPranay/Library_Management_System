package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int copies;
    private int available;
    private String imageURL;

    public Book(int id,String title,String author,String isbn,int copies,int available, String imageURL) {
        this.author = author;
        this.available = available;
        this.copies = copies;
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.imageURL = imageURL;
    }

    public Book(String title,String author,String isbn,int copies,int available, String imageURL) {
        this.author = author;
        this.available = available;
        this.copies = copies;
        this.isbn = isbn;
        this.title = title;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public int getAvailable() {
        return available;
    }

    public int getCopies() {
        return copies;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
