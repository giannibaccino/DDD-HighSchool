package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.commands.UpdateTeacherAvailability;

public class UpdateTeacherAvailabilityUseCase extends UseCase<RequestCommand<UpdateTeacherAvailability>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<UpdateTeacherAvailability> updateTeacherAvailabilityRequestCommand) {
        var command = updateTeacherAvailabilityRequestCommand.getCommand();
        var classroom = Class.from(command.getClassID(), repository().getEventsBy(command.getClassID().value()));

        classroom.updateTeacherAvailability(command.getTeacherID(), command.getAvailability());
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
