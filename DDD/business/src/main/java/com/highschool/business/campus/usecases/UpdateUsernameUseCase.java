package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.UpdateUsername;

public class UpdateUsernameUseCase extends UseCase<RequestCommand<UpdateUsername>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateUsername> updateUsernameRequestCommand) {
        var command = updateUsernameRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.updateUsername(command.getUserID(), command.getUsername());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
