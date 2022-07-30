package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.UpdateReaderName;

public class UpdateReaderNameUseCase extends UseCase<RequestCommand<UpdateReaderName>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateReaderName> updateReaderNameRequestCommand) {
        var command = updateReaderNameRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.updateReaderName(command.getReaderID(), command.getReaderName());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
