package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.library.commands.UpdateBookCategory;
import com.highschool.domain.library.commands.UpdateLoanLimitDate;
import com.highschool.domain.library.commands.UpdateLoanStatus;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.*;
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
class UpdateLoanLimitDateUseCaseTest {

    @InjectMocks
    private UpdateLoanLimitDateUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    public void updateLoanLimitDateTest(){
        //ARRANGE
        BookLoanID loanID = BookLoanID.of("XXXX");
        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(2));
        var command = new UpdateLoanLimitDate(loanID, limitDate);

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
        var event = (LoanLimitDateUpdated) events.get(0);
        assertEquals("XXXX", event.getLoanID().value());
        assertEquals(LocalDate.now().plusMonths(2), event.getLimitDate().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}