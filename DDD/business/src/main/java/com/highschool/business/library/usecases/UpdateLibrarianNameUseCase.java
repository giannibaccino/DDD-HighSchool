package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.UpdateLibrarianName;

public class UpdateLibrarianNameUseCase extends UseCase<RequestCommand<UpdateLibrarianName>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateLibrarianName> updateLibrarianNameRequestCommand) {
        var command = updateLibrarianNameRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.updateLibrarianName(command.getLibrarianID(), command.getLibrarianName());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
