package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LoanStatus;

public class LoanStatusUpdated extends DomainEvent {

    private final BookLoanID loanID;
    private final LoanStatus loanStatus;

    public LoanStatusUpdated(BookLoanID loanID, LoanStatus loanStatus) {
        super("com.highschool.domain.library.loanstatusupdated");
        this.loanID = loanID;
        this.loanStatus = loanStatus;
    }

    //GETTERS
    public BookLoanID getLoanID() {
        return loanID;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }
}
