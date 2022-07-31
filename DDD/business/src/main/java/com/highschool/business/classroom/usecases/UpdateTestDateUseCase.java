package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.UpdateTestDate;

public class UpdateTestDateUseCase extends UseCase<RequestCommand<UpdateTestDate>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateTestDate> updateTestDateRequestCommand) {
        var command = updateTestDateRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.updateTestDate(command.getTestID(), command.getTestDate());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
