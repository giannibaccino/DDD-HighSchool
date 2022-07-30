package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.library.commands.CreateLoan;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.LoanCreated;
import com.highschool.domain.library.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateLoanUseCaseTest {

    private CreateLoanUseCase useCase;

    @BeforeEach
    public void setup() { useCase = new CreateLoanUseCase(); }

    @Test
    void createTestLoan() {
        //ARRANGE
        BookLoanID bookLoanID = BookLoanID.of("XXXX");
        Reader reader = new Reader(ReaderID.of("111"), new ReaderFullName("AAAA", "BBBB"));
        Librarian librarian = new Librarian(LibrarianID.of("222"), new LibrarianFullName("CCCC", "DDDD"));
        LoanStatus loanStatus = new LoanStatus(LoanStatusEnum.ONCOURSE);
        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(1));
        var command = new CreateLoan(bookLoanID, reader, librarian, loanStatus, limitDate);

        //ACT
        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //ASSERTS
        var createdLoan = (LoanCreated) events.get(0);
        assertEquals("XXXX", createdLoan.aggregateRootId());
        assertEquals("111", createdLoan.getReader().identity().value());
        assertEquals("AAAA BBBB", createdLoan.getReader().getReaderName().value());
        assertEquals("222", createdLoan.getLibrarian().identity().value());
        assertEquals("CCCC DDDD", createdLoan.getLibrarian().getLibrarianName().value());
        assertEquals(LoanStatusEnum.ONCOURSE, createdLoan.getLoanStatus().value());
        assertEquals(LocalDate.now().plusMonths(1), createdLoan.getLimitDate().value());
    }
}