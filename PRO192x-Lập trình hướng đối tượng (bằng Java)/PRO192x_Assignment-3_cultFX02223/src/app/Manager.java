package app;

import java.util.Scanner;

public class Manager {
    Scanner in = new Scanner(System.in);
    BookList bookList = new BookList();

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.run();
    }

    public void run() {
        while (true) {
            System.out.println("------------------------");
            System.out.println("1. Enter a new book");
            System.out.println("2. Search a book title");
            System.out.println("3. Display books");
            System.out.println("4. Borrow a book by book id");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = in.nextInt();
            switch (choice) {
            case 1:
                bookList.add();
                break;
            case 2:
                bookList.search();
                break;
            case 3:
                bookList.display();
                break;
            case 4:
                bookList.borrrow();
                break;
            case 5:
                bookList.exit();
                return;
            }
        }
    }
}
