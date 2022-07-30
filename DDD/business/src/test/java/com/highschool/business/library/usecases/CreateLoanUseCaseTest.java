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
    public void createTestLoan() {
        //ARRANGE
        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(1));

        BookLoanID loanID = BookLoanID.of("xxx");
        CreateLoan command = new CreateLoan(loanID, limitDate);

        //ACT
        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //ASSERTS
        var loanCreated = (LoanCreated) events.get(0);
        assertEquals(LocalDate.now().plusMonths(1), loanCreated.getLimitDate().value());
        assertEquals("xxx", loanCreated.aggregateRootId());
    }

    private List<DomainEvent> history() {
        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(1));

        var event = new LoanCreated(limitDate);

        event.setAggregateRootId("AAAA");
        return List.of(event);
    }
}