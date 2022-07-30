package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LoanStatus;

public class UpdateLoanStatus extends Command {

    private final BookLoanID loanID;
    private final LoanStatus loanStatus;

    public UpdateLoanStatus(BookLoanID loanID, LoanStatus loanStatus) {
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
