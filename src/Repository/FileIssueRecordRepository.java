package Repository;

import model.IssueRecord;
import storage.FileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIssueRecordRepository implements IssueRecordRepository {
    private final FileStorage fileStorage;
    private final Path ISSUE_RECORD_PATH = Paths.get("issue-record.txt");
    public FileIssueRecordRepository(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        fileStorage.createIfNotExists(ISSUE_RECORD_PATH);
    }
    @Override
    public void save(IssueRecord issueRecord) {
        String line = convertToLine(issueRecord);
        fileStorage.append(line , ISSUE_RECORD_PATH);
    }

    @Override
    public IssueRecord findByBookId() {
        return null;
    }

    @Override
    public IssueRecord findAll() {
        return null;
    }

    @Override
    public void update(IssueRecord issueRecord) {

    }
    private String convertToLine(IssueRecord issueRecord) {
        return issueRecord.getIssueId() + "," +
                issueRecord.getBookId() + "," +
                issueRecord.getBorrowerName() + "," +
                issueRecord.getIssueDate() + "," +
                issueRecord.getDueDate() + "," +
                issueRecord.isReturned();
    }
}
