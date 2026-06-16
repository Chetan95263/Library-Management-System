package Repository;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();
    Optional<Book> findById(int id);
    Optional<Book> findByTitle();
    Optional<Book> findByAuthor();
    void save(Book book);
    void update(Book updatedBook);
    void delete(Book book);

}
