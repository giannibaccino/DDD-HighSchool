package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.UpdateBookCategory;

public class UpdateBookCategoryUseCase extends UseCase<RequestCommand<UpdateBookCategory>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateBookCategory> updateBookCategoryRequestCommand) {
        var command = updateBookCategoryRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.updateBookCategory(command.getBookID(), command.getBookCategory());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
