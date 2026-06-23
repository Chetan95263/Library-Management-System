package Repository;

import model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(int id);
    Book findByTitle(String title);
    Book findByAuthor(String author);
    void save(Book book);
    void update(Book updatedBook);
    public int generateBookId();
}
