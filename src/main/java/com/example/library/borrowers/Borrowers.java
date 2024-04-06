package com.example.library.borrowers;

import java.sql.Date;

public class Borrowers {
    private int borrowerID;
    private int memberID;
    private int bookID;
    private Date borrowDate;
    private Date returnDate;

    
    public Borrowers(int borrowerID, int memberID, int bookID, Date borrowDate2, Date returnDate) {
        this.borrowerID = borrowerID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.borrowDate = borrowDate2;
        this.returnDate = returnDate;
    }

 
    public int getBorrowerID() {
        return borrowerID;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getBookID() {
        return bookID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

  
    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

