import Repository.BookRepository;
import Repository.FileBookRepository;
import Repository.FileIssueRecordRepository;
import Repository.IssueRecordRepository;
import service.BookService;
import storage.FileStorage;

import java.util.Scanner;


public class Main {
    private static void showMenu(){
        System.out.println("-----------------------------------------------------");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("====================================================");
        System.out.println("1. Add Book");
        System.out.println("2. View Book");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book");
        System.out.println("6. Exit");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FileStorage fileStorage = new FileStorage();

        BookRepository bookRepository = new FileBookRepository(fileStorage);
        IssueRecordRepository issueRepository = new FileIssueRecordRepository(fileStorage);
        BookService bookService = new BookService(bookRepository , issueRepository);
        while(true) {
            showMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.viewBook();
                    break;
                case 3:
                    bookService.issueBook();
                    break;
                case 4:
                    bookService.returnBook();
                    break;
                case 5:
                    bookService.searchBook();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}