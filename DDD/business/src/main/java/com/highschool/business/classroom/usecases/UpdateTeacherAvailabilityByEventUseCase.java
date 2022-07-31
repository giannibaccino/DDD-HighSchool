package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.classroom.Class;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.AvailabilityEnum;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.TeacherID;

public class UpdateTeacherAvailabilityByEventUseCase extends UseCase<TriggeredEvent<ClassCreated>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<ClassCreated> classCreatedTriggeredEvent) {
        var event = classCreatedTriggeredEvent.getDomainEvent();
        var classroom = Class.from(ClassID.of(event.aggregateRootId()), this.retrieveEvents());

        classroom.updateTeacherAvailabilityByEvent(event.getTeacher().identity(), new Availability(AvailabilityEnum.UNAVILABLE));
        emit().onResponse(new ResponseEvents(classroom.getUncommittedChanges()));
    }
}
