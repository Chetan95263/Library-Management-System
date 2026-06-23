package Repository;

import model.IssueRecord;

public interface IssueRecordRepository {
    void save(IssueRecord issueRecord);
    IssueRecord findByBookId();
    IssueRecord findAll();
    void update(IssueRecord issueRecord);
}
