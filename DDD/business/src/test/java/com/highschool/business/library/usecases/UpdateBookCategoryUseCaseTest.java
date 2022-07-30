package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.library.commands.UpdateBookCategory;
import com.highschool.domain.library.entities.Librarian;
import com.highschool.domain.library.entities.Reader;
import com.highschool.domain.library.events.BookAdded;
import com.highschool.domain.library.events.BookCategoryUpdated;
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
class UpdateBookCategoryUseCaseTest {

    @InjectMocks
    private UpdateBookCategoryUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateBookCategoryTest(){
        //ARRANGE
        BookLoanID loanID = BookLoanID.of("XXXX");
        BookID bookID = BookID.of("YYYY");
        BookCategory updatedCategory = new BookCategory("Children");
        var command = new UpdateBookCategory(loanID, bookID, updatedCategory);

        Mockito.when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new LoanCreated(new Reader(ReaderID.of("111"), new ReaderFullName("AAAA", "BBBB")),
                        new Librarian(LibrarianID.of("222"), new LibrarianFullName("CCCC", "DDDD")),
                        new LoanStatus(LoanStatusEnum.ONCOURSE),
                        new LoanLimitDate(LocalDate.now().plusMonths(1))),
                new BookAdded(BookID.of("YYYY"), new BookName("The Prince"), new BookDescription("Fun"), new BookCategory("Adventure")),
                new BookAdded(BookID.of("ZZZZ"), new BookName("The Princess"), new BookDescription("Boring"), new BookCategory("Thriller"))
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
        var event = (BookCategoryUpdated) events.get(0);
        assertEquals("YYYY", event.getBookID().value());
        assertEquals("Children", event.getBookCategory().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}