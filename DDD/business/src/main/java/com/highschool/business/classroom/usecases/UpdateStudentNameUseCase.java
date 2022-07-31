package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.UpdateStudentName;

public class UpdateStudentNameUseCase extends UseCase<RequestCommand<UpdateStudentName>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateStudentName> updateStudentNameRequestCommand) {
        var command = updateStudentNameRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.updateStudentName(command.getStudentID(), command.getStudentName());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
