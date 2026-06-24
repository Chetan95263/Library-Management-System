package Repository;

import model.IssueRecord;
import storage.FileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public IssueRecord findActiveIssueRecordByBookId(int id) {
        return findAll()
                .stream()
                .filter(record ->
                        record.getBookId() == id &&
                                !record.isReturned())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Record not found!"));
    }


    @Override
    public List<IssueRecord> findAll() {
        return fileStorage.readAll(ISSUE_RECORD_PATH)
                .stream()
                .map(this::convertToIssueRecord)
                .collect(Collectors.toList());
    }

    @Override
    public void update(IssueRecord updatedIssueRecord) {
        List<String> lines = findAll().stream()
                .map(issueRecord ->
                        issueRecord.getIssueId() == updatedIssueRecord.getIssueId()
                                ? updatedIssueRecord
                                : issueRecord)
                .map(this::convertToLine)
                .toList();
        fileStorage.overwrite(ISSUE_RECORD_PATH , lines);

    }

    @Override
    public int generateIssueRecordId() {
        return findAll()
                .stream()
                .mapToInt(IssueRecord::getIssueId)
                .max()
                .orElse(0) + 1;
    }

    private String convertToLine(IssueRecord issueRecord) {
        return issueRecord.getIssueId() + "," +
                issueRecord.getBookId() + "," +
                issueRecord.getBorrowerName() + "," +
                issueRecord.getIssueDate() + "," +
                issueRecord.getDueDate() + "," +
                issueRecord.isReturned();
    }
    private IssueRecord convertToIssueRecord(String line) {
        String []data = line.split(",");

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueId(Integer.parseInt(data[0]));
        issueRecord.setBookId(Integer.parseInt(data[1]));
        issueRecord.setBorrowerName(data[2]);
        issueRecord.setIssueDate(LocalDate.parse(data[3]));
        issueRecord.setDueDate(LocalDate.parse(data[4]));
        issueRecord.setReturned(Boolean.parseBoolean(data[5]));
        return issueRecord;
    }
}
