package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LoanLimitDate;
import com.highschool.domain.library.values.LoanStatus;

public class LoanLimitDateUpdated extends DomainEvent {
    private final BookLoanID loanID;
    private final LoanLimitDate limitDate;

    public LoanLimitDateUpdated(BookLoanID loanID, LoanLimitDate limitDate) {
        super("com.highschool.domain.library.loanlimitdateupdated");
        this.loanID = loanID;
        this.limitDate = limitDate;
    }

    //GETTERS
    public BookLoanID getLoanID() {
        return loanID;
    }

    public LoanLimitDate getLimitDate() {
        return limitDate;
    }
}
