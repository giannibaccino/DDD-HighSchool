package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.values.LoanLimitDate;
import com.highschool.domain.library.values.LoanStatus;

public class LoanCreated extends DomainEvent {

    private final Reader reader;
    private final Librarian librarian;
    private final LoanStatus loanStatus;
    private final LoanLimitDate limitDate;

    public LoanCreated(Reader reader, Librarian librarian, LoanStatus loanStatus,LoanLimitDate limitDate) {
        super("com.highschool.domain.library.loancreated");
        this.reader = reader;
        this.librarian = librarian;
        this.loanStatus = loanStatus;
        this.limitDate = limitDate;
    }

    //GETTERS

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
