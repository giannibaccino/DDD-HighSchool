package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.values.BookID;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.BookStatus;

public class CloseLoan extends Command {

    private  final BookLoanID loanID;
    private final BookID bookID;
    private final BookStatus bookStatus;

    public CloseLoan(BookLoanID loanID, BookID bookID, BookStatus bookStatus) {
        this.loanID = loanID;
        this.bookID = bookID;
        this.bookStatus = bookStatus;
    }

    //GETTERS

    public BookLoanID getLoanID() { return loanID; }
    public BookID getBookID() {
        return bookID;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
