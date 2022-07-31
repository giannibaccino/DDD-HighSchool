package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.AddStudent;

public class AddStudentUseCase extends UseCase<RequestCommand<AddStudent>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddStudent> addStudentRequestCommand) {
        var command = addStudentRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.addStudent(command.getStudentName());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
