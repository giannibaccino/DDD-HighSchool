package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.commands.AddCourseContent;

public class AddCourseContentUseCase extends UseCase<RequestCommand<AddCourseContent>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddCourseContent> addCourseContentRequestCommand) {
        var command = addCourseContentRequestCommand.getCommand();
        var campus = Campus.from(command.getCampusURL(), repository().getEventsBy(command.getCampusURL().value()));

        campus.addCourseContent(command.getCourseID(), command.getCourseContent());
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
