package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.commands.CreateLoan;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.LoanCreated;
import com.highschool.domain.library.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateLoanUseCaseTest {

    private CreateLoanUseCase useCase;

    @BeforeEach
    public void setUp() {
        useCase = new CreateLoanUseCase();
    }

    @Test
    public void createNewLoan() {
        BookID bookID = BookID.of("111");
        BookName bookName = new BookName("The Prince");
        BookDescription bookDescription = new BookDescription("Fun");
        BookCategory bookCategory = new BookCategory("Children");
        BookStatus bookStatus = new BookStatus(BookStatusEnum.UNAVAILABLE);
        Book book = new Book(bookID, bookName, bookDescription, bookCategory, bookStatus);

        ReaderID readerID = ReaderID.of("222");
        ReaderFullName readerName = new ReaderFullName("Peter", "Parker");
        Reader reader = new Reader(readerID, readerName);

        LibrarianID librarianID = LibrarianID.of("333");
        LibrarianFullName librarianName = new LibrarianFullName("Jess", "Banin");
        Librarian librarian = new Librarian(librarianID, librarianName);

        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(1));

        BookLoanID loanID = BookLoanID.of("xxx");
        CreateLoan command = new CreateLoan(loanID, book, reader, librarian, limitDate);

        List<DomainEvent> domainEvents = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        LoanCreated loanCreated = (LoanCreated) domainEvents.get(0);
        assertEquals("111", loanCreated.getBook().identity().value());
        assertEquals("The Prince", loanCreated.getBook().getBookName().value());
        assertEquals("Fun", loanCreated.getBook().getBookDescription().value());
        assertEquals("Children", loanCreated.getBook().getBookCategory().value());
        assertEquals(BookStatusEnum.UNAVAILABLE, loanCreated.getBook().getBookStatus().value());

        assertEquals("222", loanCreated.getReader().identity().value());
        assertEquals("Peter Parker", loanCreated.getReader().getReaderName().value());

        assertEquals("333", loanCreated.getLibrarian().identity().value());
        assertEquals("Jess Banin", loanCreated.getLibrarian().getLibrarianName().value());

        assertEquals(LocalDate.now().plusMonths(1), loanCreated.getLimitDate().value());

        assertEquals("xxx", loanCreated.aggregateRootId());
    }
}