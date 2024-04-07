package com.example.library.borrowers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowersController {

    private final BorrowersService borrowersService;

    @Autowired
    public BorrowersController(BorrowersService borrowersService) {
        this.borrowersService = borrowersService;
    }

    // Endpoint to add a new borrower
    @PostMapping("/add")
    public void addBorrower(@RequestBody Borrowers borrower) throws SQLException {
        borrowersService.addBorrower(borrower);
    }

    

    @GetMapping("/borrowers")
    public String displayAllBorrowedBooks(Model model) {
        List<Borrowers> borrowers = borrowersService.getAllBorrowedBooks();
        model.addAttribute("borrowers", borrowers);
        return "borrowedBooks"; // Assuming you have a Thymeleaf template named "borrowedBooks"
    }


    // Endpoint to check if a member has borrowed any books
    @GetMapping("/hasBorrowed/{memberID}")
    public boolean hasBorrowedBooks(@PathVariable int memberID) throws SQLException {
        return borrowersService.hasBorrowedBooks(memberID);
    }

    // Endpoint to remove a borrower record
    @DeleteMapping("/remove/{bookID}/{memberID}")
    public void removeBorrowerRecord(@PathVariable int bookID, @PathVariable int memberID) throws SQLException {
        borrowersService.removeBorrowerRecord(bookID, memberID);
    }

    // Additional endpoints can be added here as needed
}

