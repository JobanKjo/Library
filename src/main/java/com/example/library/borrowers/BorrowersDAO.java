package com.example.library.borrowers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class BorrowersDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BorrowersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addBorrower(Borrowers borrower) {
        String query = "INSERT INTO Borrowers (MemberID, BookID, BorrowDate, ReturnDate) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, borrower.getMemberID(), borrower.getBookID(), borrower.getBorrowDate(), borrower.getReturnDate());
    }

    public List<Borrowers> getAllBorrowedBooks() {
        String query = "SELECT * FROM Borrowers";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            int borrowerID = resultSet.getInt("BorrowerID");
            int memberID = resultSet.getInt("MemberID");
            int bookID = resultSet.getInt("BookID");
            Date borrowDate = resultSet.getDate("BorrowDate");
            Date returnDate = resultSet.getDate("ReturnDate");
            return new Borrowers(borrowerID, memberID, bookID, borrowDate, returnDate);
        });
    }

    public boolean hasBorrowedBooks(int memberID) {
        String query = "SELECT COUNT(*) FROM Borrowers WHERE MemberID = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, memberID) > 0;
    }

    public void removeBorrowerRecord(int bookID, int memberID) {
        String query = "DELETE FROM Borrowers WHERE BookID = ? AND MemberID = ?";
        jdbcTemplate.update(query, bookID, memberID);
    }
}
