package Repository;

import model.Book;

import java.util.List;
import java.util.Optional;

public class FileBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findByTitle() {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findByAuthor() {
        return Optional.empty();
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book updatedBook) {

    }

    @Override
    public void delete(Book book) {

    }
}
