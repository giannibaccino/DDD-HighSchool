package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.AddUser;

public class AddUserUseCase extends UseCase<RequestCommand<AddUser>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddUser> addUserRequestCommand) {
        var command = addUserRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.addUser(command.getUsername(), command.getUserPassword(), command.getEmail());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
