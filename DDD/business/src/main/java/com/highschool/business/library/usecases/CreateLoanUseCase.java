package com.highschool.business.library.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.library.BookLoan;
import com.highschool.domain.library.commands.CreateLoan;

public class CreateLoanUseCase extends UseCase<RequestCommand<CreateLoan>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateLoan> createLoanRequestCommand) {
        var command = createLoanRequestCommand.getCommand();
        var loan = new BookLoan(command.getLoanID(), command.getReader(), command.getLibrarian(), command.getLoanStatus(), command.getLimitDate());

        emit().onResponse(new ResponseEvents(loan.getUncommittedChanges()));
    }
}
