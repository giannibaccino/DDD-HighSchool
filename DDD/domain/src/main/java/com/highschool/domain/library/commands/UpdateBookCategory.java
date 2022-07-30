package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookCategory;
import com.highschool.domain.library.values.BookID;
import com.highschool.domain.library.values.BookLoanID;

public class UpdateBookCategory extends Command {

    private final BookLoanID loanID;
    private final BookID bookID;
    private final BookCategory bookCategory;

    public UpdateBookCategory(BookLoanID loanID, BookID bookID, BookCategory bookCategory) {
        this.loanID = loanID;
        this.bookID = bookID;
        this.bookCategory = bookCategory;
    }

    //GETTERS

    public BookLoanID getLoanID() {
        return loanID;
    }

    public BookID getBookID() {
        return bookID;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }
}
