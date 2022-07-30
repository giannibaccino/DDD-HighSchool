package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LoanLimitDate;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.values.LoanStatus;

public class CreateLoan extends Command {

    private final BookLoanID loanID;
    private final Reader reader;
    private final Librarian librarian;
    private final LoanStatus loanStatus;
    private final LoanLimitDate limitDate;

    public CreateLoan(BookLoanID loanID, Reader reader, Librarian librarian, LoanStatus loanStatus, LoanLimitDate limitDate) {
        this.loanID = loanID;
        this.reader = reader;
        this.librarian = librarian;
        this.loanStatus = loanStatus;
        this.limitDate = limitDate;
    }

    //GETTERS

    public BookLoanID getLoanID() {
        return loanID;
    }

    public Reader getReader() {
        return reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LoanLimitDate getLimitDate() {
        return limitDate;
    }
}
