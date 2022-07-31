package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LoanLimitDate;

public class UpdateLoanLimitDate extends Command {

    private final BookLoanID loanID;
    private final LoanLimitDate limitDate;

    public UpdateLoanLimitDate(BookLoanID loanID, LoanLimitDate limitDate) {
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
