package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.UpdateLoanStatus;

public class UpdateLoanStatusUseCase extends UseCase<RequestCommand<UpdateLoanStatus>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateLoanStatus> updateLoanStatusRequestCommand) {
        var command = updateLoanStatusRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.updateLoanStatus(command.getLoanID(), command.getLoanStatus());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
