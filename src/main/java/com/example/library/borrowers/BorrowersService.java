package com.example.library.borrowers;

import java.sql.SQLException;
import java.util.List;

public class BorrowersService {
    private final BorrowersDAO borrowersDAO;

    public BorrowersService(BorrowersDAO borrowersDAO) {
        this.borrowersDAO = borrowersDAO;
    }

    public void addBorrower(Borrowers borrower) {
        borrowersDAO.addBorrower(borrower);
    }

    public List<Borrowers> getAllBorrowedBooks() {
        return borrowersDAO.getAllBorrowedBooks();
    }

    public boolean hasBorrowedBooks(int memberID) {
        return borrowersDAO.hasBorrowedBooks(memberID);
    }

    public void removeBorrowerRecord(int bookID, int memberID) {
        borrowersDAO.removeBorrowerRecord(bookID, memberID);
    }
}
