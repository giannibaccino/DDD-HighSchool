package com.highschool.domain.library;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.*;
import com.highschool.domain.library.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BookLoan extends AggregateEvent<BookLoanID> {

    protected Set<Book> books;
    protected Reader reader;
    protected Librarian librarian;
    protected LoanStatus loanStatus;
    protected LoanLimitDate limitDate;

    public BookLoan(BookLoanID bookLoanID, Reader reader, Librarian librarian, LoanStatus loanStatus, LoanLimitDate limitDate) {
        super(bookLoanID);
        appendChange(new LoanCreated(reader, librarian, loanStatus, limitDate)).apply();
        subscribe(new BookLoanChange(this));
    }

    private BookLoan(BookLoanID loanId) {
        super(loanId);
        subscribe(new BookLoanChange(this));
    }

    public static BookLoan from(BookLoanID loanId, List<DomainEvent> events) {
        BookLoan loan = new BookLoan(loanId);
        events.forEach(loan::applyEvent);
        return loan;
    }

    //EVENTS
    public void updateLoanStatus(BookLoanID loanID, LoanStatus loanStatus) {
        appendChange(new LoanStatusUpdated(loanID, loanStatus)).apply();
    }

    public void updateLoanLimitDate(BookLoanID loanID, LoanLimitDate limitDate) {
        appendChange(new LoanLimitDateUpdated(loanID, limitDate)).apply();
    }

    public void addBook(BookName bookName, BookDescription bookDescription, BookCategory bookCategory) {
        BookID bookID = new BookID();
        appendChange(new BookAdded(bookID, bookName, bookDescription, bookCategory)).apply();
    }

    public void updateBookCategory(BookID bookID, BookCategory bookCategory) {
        appendChange(new BookCategoryUpdated(bookID, bookCategory)).apply();
    }

    public void updateReaderName(ReaderID readerID, ReaderFullName readerName) {
        appendChange(new ReaderNameUpdated(readerID, readerName)).apply();
    }

    public void updateLibrarianName(LibrarianID librarianID, LibrarianFullName librarianName) {
        appendChange(new LibrarianNameUpdated(librarianID, librarianName)).apply();
    }

    //GETTERS
    protected Optional<Book> findBookById(BookID bookID) {
        return this.books.stream().filter(book -> book.identity().equals(bookID)).findFirst();
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
