# Library Management System

A console-based Library Management System developed in Java as part of an internship project. The primary objective of this project is to demonstrate the practical implementation of **File Handling**, **Repository Design**, and **Java Streams API** while maintaining a clean and scalable project structure.

Instead of using a database, all data is persisted in text files to showcase file-based storage and data management.

---

## Project Objective

This project was built with a strong focus on:

* File Handling in Java
* Layered Architecture
* Repository Pattern
* Java Streams API
* Object-Oriented Programming (OOP)
* Data Persistence without a Database

The application runs entirely in the console because the main goal was to understand how data can be stored, retrieved, and managed using files.

---

## Features

### Book Management

* Add new books
* View all books
* Search books by title
* Automatically generate unique book IDs

### Issue & Return Management

* Issue books to users
* Return issued books
* Prevent duplicate book issues
* Track active issue records

### Issue Record Tracking

* View all issue records
* Maintain issue history
* Identify currently issued books

### File-Based Persistence

* Store books in `books.txt`
* Store issue records in `issue-record.txt`
* Preserve data between application runs

---

## Project Structure

```text
src
│
├── model
│   ├── Book.java
│   └── IssueRecord.java
│
├── Repository
│   ├── BookRepository.java
│   ├── FileBookRepository.java
│   ├── IssueRecordRepository.java
│   └── FileIssueRecordRepository.java
│
├── service
│   └── BookService.java
│
├── storage
│   └── FileStorage.java
│
└── Main.java
```

---

## Architecture Overview

### Model Layer

Contains application entities:

* Book
* IssueRecord

### Repository Layer

Responsible for managing data operations.

Examples:

* Save books
* Retrieve books
* Update book details
* Manage issue records

File-based repository implementations:

* FileBookRepository
* FileIssueRecordRepository

### Service Layer

Contains business logic such as:

* Issuing books
* Returning books
* Searching books
* Validating operations

### Storage Layer

The `FileStorage` class centralizes all file operations.

Responsibilities:

* Reading data from files
* Writing new records
* Overwriting existing records
* File creation and management

This keeps file handling logic separate from business logic.

---

## File Storage Design

The project stores data in text files instead of a database.

### Books Storage

```text
books.txt
```

Stores all book information.

### Issue Records Storage

```text
issue-record.txt
```

Stores book issue and return records.

Data flow:

```text
Console Application
        ↓
Service Layer
        ↓
Repository Layer
        ↓
FileStorage
        ↓
Text Files
```

---

## Java Concepts Used

* Object-Oriented Programming (OOP)
* Interfaces
* Abstraction
* Encapsulation
* Repository Pattern
* Java Collections Framework
* Java Streams API
* Lambda Expressions
* File Handling (Java NIO)
* Exception Handling

---

## Sample Menu

```text
===== Library Management System =====

1. Add Book
2. View Books
3. Search Book
4. Issue Book
5. Return Book
6. View Issue Records
7. Exit
```

---

## Key Learning Outcomes

Through this project, I gained practical experience in:

* Designing layered Java applications
* Implementing file-based data persistence
* Separating business logic from storage logic
* Using Java Streams for filtering and searching
* Applying repository-based architecture
* Managing application data without a database

---

## Author

**Chetan Kumar**

Developed as an internship project to strengthen Java fundamentals, File Handling, Repository Pattern implementation, and clean application architecture.
