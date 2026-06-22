package Repository;

import model.Book;
import storage.FileStorage;

import java.util.List;
import java.util.Optional;

public class FileBookRepository implements BookRepository {
    private final FileStorage fileStorage = new FileStorage();

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
        String line = convertToLine(book);
        fileStorage.addBookToFile(line);
    }

    @Override
    public void update(Book updatedBook) {

    }

    @Override
    public void delete(Book book) {

    }
    @Override
    public int generateBookId() {
        return (int) (Math.random() * 100);
    }
    private String convertToLine(Book book) {
        return book.getId() + "," +
                book.getTitle() + "," +
                book.getAuthor() + "," +
                book.isIssued();
    }
}
