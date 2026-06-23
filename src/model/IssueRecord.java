package model;

import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private int bookId;
    private String borrowerName;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned;

    public int getIssueId() {
        return issueId;
    }
    public int getBookId() {
        return bookId;
    }
    public String getBorrowerName(){
        return borrowerName;
    }
    public LocalDate getIssueDate(){
        return issueDate;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public boolean isReturned() {
        return returned;
    }
    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
