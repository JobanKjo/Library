package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    // Method to fetch all books
    public List<Book> findAll() throws SQLException {
        return bookDAO.getAllBooks();
    }

    // Method to add a new book
    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    // Method to update an existing book
    public void updateBook(Book book) throws SQLException {
        bookDAO.updateBook(book);
    }

    // Method to delete a book
    public void deleteBook(int bookID) throws SQLException {
        bookDAO.deleteBook(bookID);
    }

    // Method to get a book by its ID
    public Book getBookById(int bookID) throws SQLException {
        return bookDAO.getBookById(bookID);
    }

    // Method to handle the return of a book
    public void returnBook(int bookID, int memberID) throws SQLException {
        bookDAO.returnBook(bookID, memberID);
    }
}
