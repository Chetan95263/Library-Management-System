package storage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileStorage {
    private final Path bookPath = Paths.get("books.txt");
    public FileStorage(){
        isFileExist();
    }
    public void addBookToFile(String line) {
        try {
            Files.writeString(bookPath, line + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getAllBooks(){
        try {
            return Files.readAllLines(bookPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void isFileExist() {
        if(Files.notExists(bookPath)){
            try {
                Files.createFile(bookPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
