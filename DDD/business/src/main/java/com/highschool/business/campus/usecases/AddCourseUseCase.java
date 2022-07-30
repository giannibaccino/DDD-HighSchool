package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.AddCourse;

public class AddCourseUseCase extends UseCase<RequestCommand<AddCourse>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddCourse> addCourseRequestCommand) {
        var command = addCourseRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.addCourse(command.getCourseName(), command.getCoursePassword());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
