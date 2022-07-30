package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.values.*;

public class AddBook extends Command {

    private final BookLoanID loanID;
    private final BookName bookName;
    private final BookDescription bookDescription;
    private final BookCategory bookCategory;

    public AddBook(BookLoanID loanID, BookName bookName, BookDescription bookDescription, BookCategory bookCategory) {
        this.loanID = loanID;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
    }

    //GETTERS
    public BookLoanID getLoanID() { return loanID; }

    public BookName getBookName() {
        return bookName;
    }

    public BookDescription getBookDescription() {
        return bookDescription;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

}
