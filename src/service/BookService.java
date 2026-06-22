package service;

import Repository.BookRepository;
import Repository.FileBookRepository;
import model.Book;
import storage.FileStorage;

import java.util.Scanner;

public class BookService {
    private final BookRepository bookRepository = new FileBookRepository();
    public void addBook() {
        Scanner sc = new Scanner(System.in);
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
    public void viewBook() {}
    public void issueBook() {}
    public void returnBook() {}
    public void searchBook() {}
}
