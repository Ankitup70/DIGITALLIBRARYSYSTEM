import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }
}

class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static User currentUser;

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            System.out.println("Welcome to the Digital Library Management System!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    quit = true;
                    System.out.println("Thank you for using the Digital Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        // Simulating admin authentication
        if (username.equals("admin") && password.equals("password")) {
            boolean quit = false;

            while (!quit) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. Remove Book");
                System.out.println("3. View All Books");
                System.out.println("4. Logout");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        removeBook();
                        break;
                    case 3:
                        viewAllBooks();
                        break;
                    case 4:
                        quit = true;
                        System.out.println("Admin logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }
        } else {
            System.out.println("Invalid admin username or password. Access denied!");
        }
    }

    private static void userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Simulating user authentication
        currentUser = new User(username, email);
        userMenu();
    }

    private static void userMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View All Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Email Query");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    emailQuery();
                    break;
                case 6:
                    quit = true;
                    System.out.println("User logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book book = new Book(title, author, quantity);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        List<Book> books = library.getAllBooks();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                library.removeBook(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void viewAllBooks() {
        List<Book> books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println();
            }
        }
    }

    private static void searchBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        List<Book> books = library.getAllBooks();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void issueBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        List<Book> books = library.getAllBooks();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                System.out.println("Book issued successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not available or already issued.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        List<Book> books = library.getAllBooks();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setQuantity(book.getQuantity() + 1);
                System.out.println("Book returned successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void emailQuery() {
        System.out.print("Enter your query: ");
        String query = scanner.nextLine();

        System.out.println("Query sent. We will respond to your email: " + currentUser.getEmail());
    }
}
