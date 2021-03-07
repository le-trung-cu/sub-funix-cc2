package app;

public class Book {
    private String id, title, author;
    private Boolean isBrorrowed = false;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Boolean getIsBrorrowed() {
        return isBrorrowed;
    }
    public void setIsBrorrowed(Boolean isBrorrowed) {
        this.isBrorrowed = isBrorrowed;
    }
    
    public Book(String id, String title, String author, Boolean isBrorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBrorrowed = isBrorrowed;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20s%-20b", id, title, author, isBrorrowed);
    }
    
}