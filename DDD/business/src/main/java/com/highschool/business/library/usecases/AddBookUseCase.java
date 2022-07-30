package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.AddBook;

public class AddBookUseCase extends UseCase<RequestCommand<AddBook>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddBook> addBookRequestCommand) {
        var command = addBookRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.addBook(command.getBookName(), command.getBookDescription(), command.getBookCategory());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
