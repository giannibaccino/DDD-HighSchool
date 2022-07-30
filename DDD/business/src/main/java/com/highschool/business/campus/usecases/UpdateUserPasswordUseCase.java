package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.UpdateUserPassword;

public class UpdateUserPasswordUseCase extends UseCase<RequestCommand<UpdateUserPassword>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateUserPassword> updateUserPasswordRequestCommand) {
        var command = updateUserPasswordRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.updateUserPassword(command.getUserID(), command.getUserPassword());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
