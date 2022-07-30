package com.highschoolddd.domain.library;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschoolddd.domain.library.entities.Book;
import com.highschoolddd.domain.library.entities.Librarian;
import com.highschoolddd.domain.library.entities.Reader;
import com.highschoolddd.domain.library.events.LoanCreated;
import com.highschoolddd.domain.library.values.BookLoanID;
import com.highschoolddd.domain.library.values.LoanLimitDate;

import java.util.List;

public class BookLoan extends AggregateEvent<BookLoanID> {

    protected Book book;
    protected Reader reader;
    protected Librarian librarian;
    protected LoanLimitDate limitDate;

    public BookLoan(BookLoanID loanId, Book book, Reader reader, Librarian librarian, LoanLimitDate limitDate) {
        super(loanId);
        appendChange(new LoanCreated(book, reader, librarian, limitDate)).apply();
    }

    private BookLoan(BookLoanID loanId) {
        super(loanId);
        subscribe(new BookLoanChange(this));
    }

    public static BookLoan from(BookLoanID loanId, List<DomainEvent> domainEvents) {
        BookLoan loan = new BookLoan(loanId);
        domainEvents.forEach(loan::applyEvent);
        return loan;
    }

    //GETTERS
    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public LoanLimitDate getLimitDate() {
        return limitDate;
    }
}
