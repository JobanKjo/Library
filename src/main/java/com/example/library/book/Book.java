package com.example.library.book;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int publishYear;
    private int availableCopies;

    // Constructor
    public Book(int bookID, String title, String author, String isbn, String publisher, int publishYear, int availableCopies) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.availableCopies = availableCopies;
    }
    
    public Book() {
    	
    }

    // Getters
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // Setters
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}

