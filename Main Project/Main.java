
import java.nio.file.Paths;
import java.util.Scanner;

import Constants.LiteratureType;
import Constants.ServiceType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import Library.Library;
import Pojo.Book;
import Pojo.Comic;
import Services.ComicServices;
import Services.LibraryServices;
import Services.BookServices;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Path[] paths = new Path[] { Paths.get("Data/Literature.txt"), Paths.get("Data/BorrowingLedger.txt") };
    static Library library = new Library();
    static ComicServices comicServices = new ComicServices(library);
    static BookServices bookServices = new BookServices(library);

    public static void main(String[] args) {
        try {
            loadLiterature();
            loadBorrowingLedger();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println(library.toString());
        printLibrary();
        openLibrary(library);
    }

    public static void loadLiterature() throws IOException {
        Files.lines(paths[0]).forEach(line -> {
            String[] words = line.split("--");
            if (words[0].equals(LiteratureType.Book.toString())) {
                bookServices.addLiterature(new Book(Integer.parseInt(words[1]), words[2],
                        words[3], words[4], words[5],
                        (Integer.parseInt(words[6]))));
            } else {
                comicServices.addLiterature(
                        new Comic(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3],
                                words[4], words[5],
                                (Integer.parseInt(words[6]))));

            }
        });
    }

    public static void loadBorrowingLedger() throws IOException {
        Files.lines(paths[1]).forEach(line -> {
            String[] words = line.split("--");
            Boolean serviceType = words[1].equals(LiteratureType.Book.toString());
            LibraryServices services = serviceType ? bookServices : comicServices;
            if (words[0].equals(ServiceType.Borrow.toString())) {
                services.borrow(words[3], Integer.parseInt(words[2]));
            } else {
                services.returnLit(words[3], Integer.parseInt(words[2]));
            }
        });

    }

    public static void openLibrary(Library library) {
        welcomeMsg();
        String response = scan.nextLine();
        while (!response.equals("exit")) {
            switch (response) {
                case "show":
                    printLibrary();
                    break;
                case "filter":
                    filterLibrary(library);
                    break;
                case "borrow":
                    borrow();
                    break;
                case "return":
                    returnLit();
                    break;
                default:
                    System.out.println("wrong choice");

            }
            System.out.println("Press any key to continue");
            scan.nextLine();
            welcomeMsg();
            response = scan.nextLine();
        }
    }

    public static void borrow() {
        LibraryServices service;
        String literatureName = getLiterature();
        int amount = scanAmount();
        if (!bookServices.retrieveLiterature(literatureName).getIsbn().equals(null)) {
            service = bookServices;
        } else {
            service = comicServices;
        }
        service.borrow(literatureName, amount);
    }

    public static void returnLit() {
        LibraryServices service;
        String literatureName = getLiterature();
        int amount = scanAmount();
        if (!bookServices.retrieveLiterature(literatureName).getIsbn().equals(null)) {
            service = bookServices;
        } else {
            service = comicServices;
        }
        service.returnLit(literatureName, amount);
    }

    /*
     * public static LibraryServices selectLiteratureType() {
     * System.out.println("\nSelect 'comic' or a 'book'");
     * String literatureType = scan.nextLine();
     * while (true) {
     * if (literatureType.toLowerCase().equals("book")) {
     * return bookServices;
     * } else if (literatureType.toLowerCase().equals("comic")) {
     * return comicServices;
     * } else {
     * System.out.println("\nSelect 'comic' or a 'book'");
     * literatureType = scan.nextLine();
     * continue;
     * }
     * }
     * }
     */

    public static String getLiterature() {
        System.out.println("\nEnter title you wish to borrow, watch out for upper case!");
        String literatureName = scan.nextLine();
        while (true) {
            if (library.contains(literatureName)) {
                return literatureName;
            } else {
                System.out.println("Wrong name!");
                literatureName = scan.nextLine();
                continue;
            }

        }
    }

    public static int scanAmount() {
        System.out.println("\nSelect amount");
        while (true) {
            if (scan.hasNextInt()) {
                int amount = scan.nextInt();
                return amount;
            } else {
                scan.nextLine();
                System.out.println("\nSelect amount with a number");
                continue;
            }
        }

    }

    public static void filterLibrary(Library library) {
        System.out.println("\nInsert what are you looking for.");
        String searchParameter = scan.nextLine();
        System.out.println("\nSearch has found:");
        String filteredLibrary = library.filter(searchParameter);
        if (filteredLibrary.equals("")) {
            System.out.println("\nNothing found");
        } else {
            System.out.println(filteredLibrary);
        }
    }

    public static void printLibrary() {
        System.out.println("\n" + library.toString());
    }

    public static void welcomeMsg() {
        System.out.println(
                "\nWelcome to M.S. Library. \n Type in: 'show' to see what we can offer. \n Type 'filter' to filter among our books. \n Type 'borrow' if you wish to borrow a book. \n Type 'return' if you wish to return a book. \n type 'exit' to exit library.");
    }
}