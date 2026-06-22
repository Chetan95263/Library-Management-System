package Repository;

import model.Book;
import storage.FileStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBookRepository implements BookRepository {
    private final FileStorage fileStorage = new FileStorage();

    @Override
    public List<Book> findAll() {
        return fileStorage.getAllBooks()
                .stream()
                .map(this::convertToBook)
                .collect(Collectors.toList());
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
          return findAll()
                  .stream()
                  .mapToInt(Book::getId)
                  .max()
                  .orElse(0) + 1;
    }
    private String convertToLine(Book book) {
        return book.getId() + "," +
                book.getTitle() + "," +
                book.getAuthor() + "," +
                book.isIssued();
    }
    private Book convertToBook(String line) {
        String []data = line.split(",");
        Book book = new Book();
        book.setId(Integer.parseInt(data[0]));
        book.setTittle(data[1]);
        book.setAuthor(data[2]);
        book.setIssued(Boolean.parseBoolean(data[3]));
        return book;
    }
}
