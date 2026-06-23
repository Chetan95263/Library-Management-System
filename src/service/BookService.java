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
        System.out.println("Enter Book Author: ");
        String author = sc.nextLine();

        Book book = new Book();
        book.setId(bookRepository.generateBookId());
        book.setTittle(title.toLowerCase());
        book.setAuthor(author.toLowerCase());
        book.setIssued(false);
        bookRepository.save(book);
    }
    public void viewBook() {
        List<Book> bookList = bookRepository.findAll();
        bookList.forEach(System.out::println);
    }
    public void issueBook() {
        System.out.println("Enter book title: ");
        String title = sc.nextLine();
        System.out.println("Enter borrower name: ");
        String borrowerName = sc.nextLine();

        Book book = bookRepository.findByTitle(title);

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBookId(book.getId());
        issueRecord.setBorrowerName(borrowerName);
        issueRecord.setIssueId(34232);
        issueRecord.setReturned(false);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecordRepository.save(issueRecord);
    }
    public void returnBook() {}
    public void searchBook() {}
}
