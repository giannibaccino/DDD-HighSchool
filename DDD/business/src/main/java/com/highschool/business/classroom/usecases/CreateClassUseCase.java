package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.CreateClass;

public class CreateClassUseCase extends UseCase<RequestCommand<CreateClass>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateClass> createClassRequestCommand) {
        var command = createClassRequestCommand.getCommand();
        var classroom = new Class(command.getClassID(), command.getTeacher());

        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
