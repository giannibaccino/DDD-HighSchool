package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.UpdateTeacherName;

public class UpdateTeacherNameUseCase extends UseCase<RequestCommand<UpdateTeacherName>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateTeacherName> updateTeacherNameRequestCommand) {
        var command = updateTeacherNameRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.updateTeacherName(command.getTeacherID(), command.getTeacherName());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
