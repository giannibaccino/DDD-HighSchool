package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.AddTest;

public class AddTestUseCase extends UseCase<RequestCommand<AddTest>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddTest> addTestRequestCommand) {
        var command = addTestRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.addTest(command.getTestDate());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
