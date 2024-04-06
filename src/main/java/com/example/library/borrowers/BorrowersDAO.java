package com.example.library.borrowers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BorrowersDAO {
    private Connection connection;


    public BorrowersDAO(Connection connection) {
        this.connection = connection;
    }


	public void addBorrower(Borrowers borrower) throws SQLException {
        String query = "INSERT INTO Borrowers (MemberID, BookID, BorrowDate, ReturnDate) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, borrower.getMemberID());
        statement.setInt(2, borrower.getBookID());
        statement.setDate(3, new java.sql.Date(borrower.getBorrowDate().getTime()));
        statement.setDate(4, borrower.getReturnDate() != null ? new java.sql.Date(borrower.getReturnDate().getTime()) : null);
        statement.executeUpdate();
    }
    
    public void displayAllBorrowedBooks() throws SQLException {
        String query = "SELECT * FROM Borrowers";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        System.out.println("Borrowed Books:");
        while (resultSet.next()) {
            int memberID = resultSet.getInt("MemberID");
            int bookID = resultSet.getInt("BookID");
            java.sql.Date borrowDate = resultSet.getDate("BorrowDate");
            java.sql.Date returnDate = resultSet.getDate("ReturnDate");
            System.out.println("Member ID: " + memberID + ", Book ID: " + bookID + ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate);
        }
    }
    
    public boolean hasBorrowedBooks(int memberID) {
        String query = "SELECT COUNT(*) FROM borrowers WHERE MemberID = ?";
        try {
        	PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
   
    public void removeBorrowerRecord(int bookID, int memberID) throws SQLException {
        String query = "DELETE FROM borrowers WHERE BookID = ? AND MemberID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            stmt.setInt(2, memberID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Borrower record removed successfully.");
            } else {
                System.out.println("No borrower record found to remove.");
            }
        }
    }


}
