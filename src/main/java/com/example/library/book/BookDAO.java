package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String query = "SELECT * FROM Books";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            int bookID = resultSet.getInt("BookID");
            String title = resultSet.getString("Title");
            String author = resultSet.getString("Author");
            String isbn = resultSet.getString("ISBN");
            String publisher = resultSet.getString("Publisher");
            int publishYear = resultSet.getInt("PublishYear");
            int availableCopies = resultSet.getInt("AvailableCopies");
            return new Book(bookID, title, author, isbn, publisher, publishYear, availableCopies);
        });
    }

    public void addBook(Book book) {
        String query = "INSERT INTO Books (Title, Author, ISBN, Publisher, PublishYear, AvailableCopies) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublisher(), book.getPublishYear(), book.getAvailableCopies());
    }

    public void updateBook(Book book) {
        String query = "UPDATE Books SET Title = ?, Author = ?, ISBN = ?, Publisher = ?, PublishYear = ?, AvailableCopies = ? WHERE BookID = ?";
        jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublisher(), book.getPublishYear(), book.getAvailableCopies(), book.getBookID());
    }

    public void deleteBook(int bookID) {
        String query = "DELETE FROM Books WHERE BookID = ?";
        jdbcTemplate.update(query, bookID);
    }

    @SuppressWarnings("deprecation")
	public Book getBookById(int bookID) {
        String query = "SELECT * FROM Books WHERE BookID = ?";
        List<Book> books = jdbcTemplate.query(query, new Object[]{bookID}, (resultSet, rowNum) -> {
            int id = resultSet.getInt("BookID");
            String title = resultSet.getString("Title");
            String author = resultSet.getString("Author");
            String isbn = resultSet.getString("ISBN");
            String publisher = resultSet.getString("Publisher");
            int publishYear = resultSet.getInt("PublishYear");
            int availableCopies = resultSet.getInt("AvailableCopies");
            return new Book(id, title, author, isbn, publisher, publishYear, availableCopies);
        });

        if (!books.isEmpty()) {
            return books.get(0); // Assuming only one book will match the ID
        } else {
            return null; // No book found with the given ID
        }
    }


    public void returnBook(int bookID, int memberID) {
        Book bookToReturn = getBookById(bookID);
        if (bookToReturn != null) {
            int currentCopies = bookToReturn.getAvailableCopies();
            bookToReturn.setAvailableCopies(currentCopies + 1);
            updateBook(bookToReturn);

            String query = "UPDATE Borrowers SET ReturnDate = ? WHERE BookID = ? AND MemberID = ?";
            jdbcTemplate.update(query, new java.sql.Date(System.currentTimeMillis()), bookID, memberID);

            System.out.println("Book returned successfully.");
            // Assuming you have a method to remove borrower record
            // obj.removeBorrowerRecord(bookID, memberID);
        } else {
            System.out.println("Book not found.");
        }
    }
}





//package com.example.library.book;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.example.library.borrowers.BorrowersDAO;
//@Component
//public class BookDAO {
//    private Connection connection;
//
//    
//    public BookDAO(Connection connection) {
//        this.connection = connection;
//    }
//    
//
//    
//    public List<Book> getAllBooks() throws SQLException {
//        List<Book> books = new ArrayList<>();
//        String query = "SELECT * FROM Books";
//        PreparedStatement statement = connection.prepareStatement(query);
//        ResultSet resultSet = statement.executeQuery();
//
//        while (resultSet.next()) {
//            int bookID = resultSet.getInt("BookID");
//            String title = resultSet.getString("Title");
//            String author = resultSet.getString("Author");
//            String isbn = resultSet.getString("ISBN");
//            String publisher = resultSet.getString("Publisher");
//            int publishYear = resultSet.getInt("PublishYear");
//            int availableCopies = resultSet.getInt("AvailableCopies");
//            books.add(new Book(bookID, title, author, isbn, publisher, publishYear, availableCopies));
//        }
//        return books;
//    }
//
//    
//    public void addBook(Book book) throws SQLException {
//        String query = "INSERT INTO Books (Title, Author, ISBN, Publisher, PublishYear, AvailableCopies) VALUES (?, ?, ?, ?, ?, ?)";
//        PreparedStatement statement = connection.prepareStatement(query);
//        System.out.println(book.getTitle());
//        statement.setString(1, book.getTitle());
//        statement.setString(2, book.getAuthor());
//        statement.setString(3, book.getIsbn());
//        statement.setString(4, book.getPublisher());
//        statement.setInt(5, book.getPublishYear());
//        statement.setInt(6, book.getAvailableCopies());
//        statement.executeUpdate();
//    }
//
//    
//    public void updateBook(Book book) throws SQLException {
//        String query = "UPDATE Books SET Title = ?, Author = ?, ISBN = ?, Publisher = ?, PublishYear = ?, AvailableCopies = ? WHERE BookID = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setString(1, book.getTitle());
//        statement.setString(2, book.getAuthor());
//        statement.setString(3, book.getIsbn());
//        statement.setString(4, book.getPublisher());
//        statement.setInt(5, book.getPublishYear());
//        statement.setInt(6, book.getAvailableCopies());
//        statement.setInt(7, book.getBookID());
//        statement.executeUpdate();
//    }
//
//    
//    public void deleteBook(int bookID) throws SQLException {
//        String query = "DELETE FROM Books WHERE BookID = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, bookID);
//        statement.executeUpdate();
//    }
//    
// 
//    public Book getBookById(int bookID) throws SQLException {
//        String query = "SELECT * FROM Books WHERE BookID = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, bookID);
//        ResultSet resultSet = statement.executeQuery();
//
//        if (resultSet.next()) {
//            int id = resultSet.getInt("BookID");
//            String title = resultSet.getString("Title");
//            String author = resultSet.getString("Author");
//            String isbn = resultSet.getString("ISBN");
//            String publisher = resultSet.getString("Publisher");
//            int publishYear = resultSet.getInt("PublishYear");
//            int availableCopies = resultSet.getInt("AvailableCopies");
//            return new Book(id, title, author, isbn, publisher, publishYear, availableCopies);
//        } else {
//            return null; 
//        }
//    }
//    
// 
//    public void returnBook(int bookID, int memberID) throws SQLException {
//        
//        Book bookToReturn = getBookById(bookID);
//        BorrowersDAO obj=new BorrowersDAO(connection);
//        if (bookToReturn != null) {
//            
//            int currentCopies = bookToReturn.getAvailableCopies();
//            bookToReturn.setAvailableCopies(currentCopies + 1);
//
//            updateBook(bookToReturn);
//           
//           
//            String query = "UPDATE Borrowers SET ReturnDate = ? WHERE BookID = ? AND MemberID = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
//            statement.setInt(2, bookID);
//            statement.setInt(3, memberID);
//            statement.executeUpdate();
//
//            System.out.println("Book returned successfully.");
//            obj.removeBorrowerRecord(bookID, memberID);
//        } else {
//            System.out.println("Book not found.");
//        }
//    }
//
//
////    public void returnBook(int bookID) throws SQLException {
////        
////        Book bookToReturn = getBookById(bookID);
////        if (bookToReturn != null) {
////            
////            int currentCopies = bookToReturn.getAvailableCopies();
////            bookToReturn.setAvailableCopies(currentCopies + 1);
////
////            
////            updateBook(bookToReturn);
////            System.out.println("Book returned successfully.");
////        } else {
////            System.out.println("Book not found.");
////        }
////    }
//
//}

