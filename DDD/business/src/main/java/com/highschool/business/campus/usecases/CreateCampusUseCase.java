package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.CreateCampus;

public class CreateCampusUseCase extends UseCase<RequestCommand<CreateCampus>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateCampus> createCampusRequestCommand) {
        var command = createCampusRequestCommand.getCommand();
        var campus = new Campus(command.getCampusURL(), command.getCampusStatus());

        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
