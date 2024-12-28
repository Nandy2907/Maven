import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    static class Book {
        String title;
        boolean isBorrowed;

        Book(String title) {
            this.title = title;
            this.isBorrowed = false;
        }
    }

    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title) {
        books.add(new Book(title));
        System.out.println("Book added: " + title);
    }

    public void viewBooks() {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String status = book.isBorrowed ? "(Borrowed)" : "(Available)";
            System.out.println((i + 1) + ". " + book.title + " " + status);
        }
    }

    public void borrowBook(int bookIndex) {
        if (bookIndex >= 0 && bookIndex < books.size()) {
            Book book = books.get(bookIndex);
            if (!book.isBorrowed) {
                book.isBorrowed = true;
                System.out.println("You borrowed: " + book.title);
            } else {
                System.out.println("Sorry, this book is already borrowed.");
            }
        } else {
            System.out.println("Invalid book number.");
        }
    }

    public static void main(String[] args) {
        LibraryApp library = new LibraryApp();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Library!");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    library.addBook(title);
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter book number to borrow: ");
                    int bookIndex = scanner.nextInt() - 1;
                    library.borrowBook(bookIndex);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the library. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
