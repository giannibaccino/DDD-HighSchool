package com.highschoolddd.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschoolddd.domain.library.values.BookLoanID;

public class CloseLoan extends Command {

    private final BookLoanID loanID;

    public CloseLoan(BookLoanID bookLoanID) {
        this.loanID = bookLoanID;
    }

    //GETTERS
    public BookLoanID getBookLoanID() {
        return loanID;
    }
}
