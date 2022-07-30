package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.UpdateCourseName;

public class UpdateCourseNameUseCase extends UseCase<RequestCommand<UpdateCourseName>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateCourseName> updateCourseNameRequestCommand) {
        var command = updateCourseNameRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.updateCourseName(command.getCourseID(), command.getCourseName());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
