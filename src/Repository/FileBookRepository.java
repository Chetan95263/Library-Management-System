package Repository;

import model.Book;
import storage.FileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileBookRepository implements BookRepository {
    private final FileStorage fileStorage;
    private final Path BOOK_PATH = Paths.get("books.txt") ;
    public FileBookRepository(FileStorage fileStorage){
        this.fileStorage = fileStorage;
        fileStorage.createIfNotExists(BOOK_PATH);
    }

    @Override
    public List<Book> findAll() {
        return fileStorage.readAll(BOOK_PATH)
                .stream()
                .map(this::convertToBook)
                .collect(Collectors.toList());
    }

    @Override
    public Book findById(int id) {
        return findAll()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Book not found!"));
    }

    @Override
    public Book findByTitle(String title) {
        return findAll()
                .stream()
                .filter((book) -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    @Override
    public Book findByAuthor(String author) {
         return findAll()
                 .stream()
                 .filter(book -> book.getAuthor().equals(author))
                 .findFirst()
                 .orElseThrow(()-> new RuntimeException("Book not found!"));
    }

    @Override
    public void save(Book book) {
        String line = convertToLine(book);
        fileStorage.append(line , BOOK_PATH);
    }

    @Override
    public void update(Book updatedBook) {
        List<String> lines = findAll().stream()
                .map(book ->
                        book.getId() == updatedBook.getId()
                                ? updatedBook
                                : book)
                .map(this::convertToLine)
                .toList();
        fileStorage.overwrite(BOOK_PATH , lines);
    }
    @Override
    public boolean isBookExists(String title){
        return findAll()
                .stream()
                .anyMatch(book -> book.getTitle().equalsIgnoreCase(title));
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
