package com.highschoolddd.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschoolddd.domain.library.values.BookLoanID;

public class LoanClosed extends DomainEvent {

    private final BookLoanID loanID;
    private final boolean wasClosed;

    public LoanClosed(BookLoanID loanID) {
        super("com.highschoolddd.domain.library.loanclosed");
        this.loanID = loanID;
        wasClosed = true;
    }

    //GETTERS
    public BookLoanID getLoanID() {
        return loanID;
    }

    public boolean isWasClosed() {
        return wasClosed;
    }
}
