package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.UpdateLoanLimitDate;

public class UpdateLoanLimitDateUseCase extends UseCase<RequestCommand<UpdateLoanLimitDate>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateLoanLimitDate> updateLoanLimitDateRequestCommand) {
        var command = updateLoanLimitDateRequestCommand.getCommand();
        var loan = BookLoan.from(command.getLoanID(), repository().getEventsBy(command.getLoanID().value()));

        loan.updateLoanLimitDate(command.getLoanID(), command.getLimitDate());
        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
