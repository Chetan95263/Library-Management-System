package Repository;

import model.IssueRecord;

import java.util.List;

public interface IssueRecordRepository {
    void save(IssueRecord issueRecord);
    IssueRecord findActiveIssueRecordByBookId(int id);
    List<IssueRecord> findAll();
    void update(IssueRecord issueRecord);
    public int generateIssueRecordId();
}
