package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.CloseLoan;

public class CloseLoanUseCase extends UseCase<RequestCommand<CloseLoan>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CloseLoan> closeLoanRequestCommand) {
        var command = closeLoanRequestCommand.getCommand();

        BookLoan bookLoan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));
        bookLoan.closeLoan(command.getBookID(), command.getBookStatus());
        emit().onResponse(new ResponseEvents(bookLoan.getUncommittedChanges()));
    }
}
