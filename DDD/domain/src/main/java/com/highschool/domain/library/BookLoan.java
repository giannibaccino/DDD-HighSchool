package com.highschool.domain.library;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.LoanClosed;
import com.highschool.domain.library.events.LoanCreated;
import com.highschool.domain.library.values.BookID;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.BookStatus;
import com.highschool.domain.library.values.LoanLimitDate;

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

    //EVENTS
    public void closeLoan(BookID bookID, BookStatus bookStatus) {
        appendChange(new LoanClosed(bookID, bookStatus));
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
