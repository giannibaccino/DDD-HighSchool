package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.UpdateCoursePassword;

public class UpdateCoursePasswordUseCase extends UseCase<RequestCommand<UpdateCoursePassword>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateCoursePassword> updateCoursePasswordRequestCommand) {
        var command = updateCoursePasswordRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.updateCoursePassword(command.getCourseID(), command.getCoursePassword());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
