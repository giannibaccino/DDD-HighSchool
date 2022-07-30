package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.commands.AddBook;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.BookAdded;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddBookUseCaseTest {

    @InjectMocks
    private AddBookUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addBookTest() {
        //ARRANGE
        BookLoanID bookLoanID = BookLoanID.of("XXXX");
        BookName bookName = new BookName("AAAA");
        BookDescription bookDescription = new BookDescription("BBBB");
        BookCategory bookCategory = new BookCategory("CCCC");
        var command = new AddBook(bookLoanID, bookName, bookDescription, bookCategory);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        //ACT
        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getLoanID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //ASSERTS
        var event = (BookAdded) events.get(0);
        assertEquals("AAAA", event.getBookName().value());
        assertEquals("BBBB", event.getBookDescription().value());
        assertEquals("CCCC", event.getBookCategory().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        Reader reader = new Reader(ReaderID.of("111"), new ReaderFullName("AAAA", "BBBB"));
        Librarian librarian = new Librarian(LibrarianID.of("222"), new LibrarianFullName("CCCC", "DDDD"));
        LoanStatus loanStatus = new LoanStatus(LoanStatusEnum.ONCOURSE);
        LoanLimitDate limitDate = new LoanLimitDate(LocalDate.now().plusMonths(1));
        var event = new LoanCreated(reader, librarian, loanStatus, limitDate);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}