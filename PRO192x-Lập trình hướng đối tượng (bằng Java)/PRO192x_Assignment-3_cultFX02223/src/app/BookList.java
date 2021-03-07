package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookList {
    List<Book> myBooks = new ArrayList<>();

    public BookList(){
        seedData();
    }

    private void seedData(){
        this.addToList("HW1", "The cooker", "Tim", false);
        this.addToList("HW2", "The Timer", "Steven", true);
        this.addToList("HW3", "Travel to world", "Job", false);
        this.addToList("HW4", "Food coker", "Shen", false);
        this.addToList("HW5", "See the world", "Timony", false);
    }

    public void add() {
        System.out.println("Enter information for the new book:");
        Scanner in = new Scanner(System.in);

        System.out.print("Id: ");
        String id = in.nextLine();

        System.out.print("Title: ");
        String title = in.nextLine();

        System.out.print("Author: ");
        String author = in.nextLine();

        System.out.print("Is borrowed (1 = yes, 0 = no): ");
        int choice = in.nextInt();
        Boolean isBorrowed = false;
        switch (choice) {
        case 1:
            isBorrowed = true;
            break;
        case 0:
            isBorrowed = false;
            break;
        }

        addToList(id.toUpperCase(), title, author, isBorrowed);
    }

    private void addToList(String id, String title, String author, Boolean isBorrowed) {
        Book book = new Book(id.toUpperCase(), title, author, isBorrowed);

        myBooks.add(book);
    }

    // search book
    public void search() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter book title to search.");
        System.out.print("Book title: ");
        String title = in.nextLine();
        title = title.toUpperCase();

        List<Book> books = new ArrayList<>();

        for (Book book : myBooks) {
            if (book.getTitle().toUpperCase().contains(title)) {
                books.add(book);
            }
        }

        if (books.isEmpty()) {
            System.out.println("No book is found");
        } else {
            display(books);
        }
    }

    public void borrrow() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter book Id to borrow:");
        System.out.print("Book Id: ");
        String id = in.nextLine();
        id = id.toUpperCase();

        for (Book book : myBooks) {
            if (book.getId().equals(id)) {
                if (!book.getIsBrorrowed()) {
                    book.setIsBrorrowed(true);
                    System.out.println("You have successfully borrow the book: " + book.getTitle());
                } else {
                    System.out.println("You can't borrow this book because that was borrowed");
                }

                return;
            }
        }

        System.out.println("No book is found");
    }

    public void display() {
        display(this.myBooks);
    }

    private void display(List<Book> books) {

        System.out.println(String.format("%-10s%-20s%-20s%-20s", "ID", "title", "author", "is Brorrowed"));

        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public void exit() {
        this.myBooks.clear();
    }
}
