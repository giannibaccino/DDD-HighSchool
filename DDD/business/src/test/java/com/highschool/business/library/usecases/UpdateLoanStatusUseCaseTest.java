package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.library.commands.UpdateLoanStatus;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.LoanCreated;
import com.highschool.domain.library.events.LoanStatusUpdated;
import com.highschool.domain.library.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateLoanStatusUseCaseTest {

    @InjectMocks
    private UpdateLoanStatusUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateLoanStatusTest() {
        //ARRANGE
        BookLoanID loanID = BookLoanID.of("XXXX");
        LoanStatus loanStatus = new LoanStatus(LoanStatusEnum.ENDED);
        var command = new UpdateLoanStatus(loanID, loanStatus);

        Mockito.when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new LoanCreated(new Reader(ReaderID.of("111"), new ReaderFullName("AAAA", "BBBB")),
                        new Librarian(LibrarianID.of("222"), new LibrarianFullName("CCCC", "DDDD")),
                        new LoanStatus(LoanStatusEnum.ONCOURSE),
                        new LoanLimitDate(LocalDate.now().plusMonths(1)))
        ));
        useCase.addRepository(repository);

        //ACT
        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor("XXXX")
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //ASSERTS
        var event = (LoanStatusUpdated) events.get(0);
        assertEquals("XXXX", event.getLoanID().value());
        assertEquals(LoanStatusEnum.ENDED, event.getLoanStatus().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}