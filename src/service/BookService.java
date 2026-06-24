package service;

import Repository.BookRepository;
import Repository.IssueRecordRepository;
import model.Book;
import model.IssueRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private final BookRepository bookRepository;
    private final IssueRecordRepository issueRecordRepository;
    public BookService(BookRepository bookRepository , IssueRecordRepository issueRecordRepository) {
        this.bookRepository = bookRepository;
        this.issueRecordRepository = issueRecordRepository;
    }
    private final Scanner sc = new Scanner(System.in);
    public void addBook() {
        System.out.println("Enter Book Title: ");
        String title = sc.nextLine();

        if(bookRepository.isBookExists(title.toLowerCase())) {
            throw new RuntimeException("Book is already exists!");
        }
        System.out.println("Enter Book Author: ");
        String author = sc.nextLine();

        Book book = new Book();
        book.setId(bookRepository.generateBookId());
        book.setTittle(title.toLowerCase());
        book.setAuthor(author.toLowerCase());
        book.setIssued(false);
        bookRepository.save(book);
        System.out.println("Book: "+title+" has been added successfully!");
    }
    public void viewBook() {
        List<Book> bookList = bookRepository.findAll();
        bookList.forEach(System.out::println);
    }
    public void issueBook() {
        System.out.println("Enter Book title: ");
        String title = sc.nextLine();
        System.out.println("Enter Borrower name: ");
        String borrowerName = sc.nextLine();

        Book book = bookRepository.findByTitle(title.toLowerCase());

        // checking book is already issued or not?
        if(book.isIssued()) {
            LocalDate dueDate = issueRecordRepository.findActiveIssueRecordByBookId(book.getId()).getDueDate();
            throw  new RuntimeException("Book not available! Try again after "+dueDate);
        }
        book.setIssued(true);

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBookId(book.getId());
        issueRecord.setBorrowerName(borrowerName.toLowerCase());
        issueRecord.setIssueId(issueRecordRepository.generateIssueRecordId());
        issueRecord.setReturned(false);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        bookRepository.update(book);
        issueRecordRepository.save(issueRecord);
        System.out.println("Book: "+book.getTitle()+" has been issued successfully!");
    }
    public void returnBook() {
        System.out.println("Enter Book title: ");
        String title = sc.nextLine();
        System.out.println("Enter Borrower name: ");
        String borrowerName = sc.nextLine();

        Book book = bookRepository.findByTitle(title.toLowerCase());
        IssueRecord record = issueRecordRepository.findActiveIssueRecordByBookId(book.getId());

        // checking book is already returned or not
        if(!record.getBorrowerName().equals(borrowerName.toLowerCase())) {
            throw new RuntimeException("record not available!");
        }
        if(record.isReturned()) {
            throw new RuntimeException("This book is already returned!");
        }

        // returning book
        record.setReturned(true);
        book.setIssued(false);
        bookRepository.update(book);
        issueRecordRepository.update(record);
        System.out.println("Book: " + book.getTitle() + " has been returned successfully!");
    }
    public void searchBook() {

        System.out.println("1. Search Book by Id");
        System.out.println("2. Search Book by Title");
        System.out.println("3. Search Book by Author");
        int option = Integer.parseInt(sc.nextLine());
        if(option == 1) {
            System.out.println("Enter Book Id: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println(bookRepository.findById(id));
        } else if(option == 2) {
            System.out.println("Enter Book Title: ");
            String title = sc.nextLine();
            System.out.println(bookRepository.findByTitle(title.toLowerCase()));
        } else if(option == 3) {
            System.out.println("Enter Book Author: ");
            String author = sc.nextLine();
            System.out.println(bookRepository.findByAuthor(author.toLowerCase()));
        } else {
            System.out.println("Error: invalid option!");
        }

    }
    public void viewIssueRecords() {
        issueRecordRepository.findAll()
                .forEach(System.out::println);
    }
}
