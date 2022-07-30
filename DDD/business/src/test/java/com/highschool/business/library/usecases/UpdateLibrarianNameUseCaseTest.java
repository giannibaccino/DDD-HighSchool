package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.library.commands.UpdateLibrarianName;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.LibrarianNameUpdated;
import com.highschool.domain.library.events.LoanCreated;
import com.highschool.domain.library.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UpdateLibrarianNameUseCaseTest {

    @InjectMocks
    private UpdateLibrarianNameUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateLibrarianNameTest() {
        //ARRANGE
        BookLoanID loanID = BookLoanID.of("XXXX");
        LibrarianID librarianID = LibrarianID.of("YYYY");
        LibrarianFullName librarianName = new LibrarianFullName("Maria", "Jensen");
        var command = new UpdateLibrarianName(loanID, librarianID, librarianName);

        Mockito.when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new LoanCreated(new Reader(ReaderID.of("111"), new ReaderFullName("AAAA", "BBBB")),
                        new Librarian(LibrarianID.of("YYYY"), new LibrarianFullName("CCCC", "DDDD")),
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
        var event = (LibrarianNameUpdated) events.get(0);
        assertEquals("YYYY", event.getLibrarianID().value());
        assertEquals("Maria Jensen", event.getLibrarianName().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}