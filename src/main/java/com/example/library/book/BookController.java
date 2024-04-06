package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) throws SQLException {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) throws SQLException {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{bookID}")
    public String showEditBookForm(@PathVariable("bookID") int bookID, Model model) throws SQLException {
        Book book = bookService.getBookById(bookID);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/edit/{bookID}")
    public String editBook(@PathVariable("bookID") int bookID, @ModelAttribute Book book) throws SQLException {
        book.setBookID(bookID);
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookID}")
    public String deleteBook(@PathVariable("bookID") int bookID) throws SQLException {
        bookService.deleteBook(bookID);
        return "redirect:/books";
    }

    @GetMapping("/return/{bookID}/{memberID}")
    public String returnBook(@PathVariable("bookID") int bookID, @PathVariable("memberID") int memberID) throws SQLException {
        bookService.returnBook(bookID, memberID);
        return "redirect:/books";
    }
}

