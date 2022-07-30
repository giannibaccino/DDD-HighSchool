package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.UpdateCampusStatus;

public class UpdateCampusStatusUseCase  extends UseCase<RequestCommand<UpdateCampusStatus>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateCampusStatus> updateCampusStatusRequestCommand) {
        var command = updateCampusStatusRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.updateCampusStatus(command.getCampusURL(), command.getCampusStatus());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
