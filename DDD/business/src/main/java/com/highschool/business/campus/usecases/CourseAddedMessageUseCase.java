package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.events.CourseAdded;
import com.highschool.domain.campus.values.CampusURL;

public class CourseAddedMessageUseCase extends UseCase<TriggeredEvent<CourseAdded>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<CourseAdded> courseAddedTriggeredEvent) {
        var event = courseAddedTriggeredEvent.getDomainEvent();
        var campus = Campus.from(CampusURL.of(event.aggregateRootId()), this.retrieveEvents());

        campus.courseAddedMessage("Course was succesfully added");
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
