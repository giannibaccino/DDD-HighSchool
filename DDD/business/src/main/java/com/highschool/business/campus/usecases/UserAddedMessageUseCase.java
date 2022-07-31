package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.campus.Campus;
import com.highschool.domain.campus.events.UserAdded;
import com.highschool.domain.campus.values.CampusURL;

public class UserAddedMessageUseCase extends UseCase<TriggeredEvent<UserAdded>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<UserAdded> userAddedTriggeredEvent) {
        var event = userAddedTriggeredEvent.getDomainEvent();
        var campus = Campus.from(CampusURL.of(event.aggregateRootId()), this.retrieveEvents());

        campus.userAddedMessage("User was succesfully added");
        emit().onResponse(new ResponseEvents(campus.getUncommittedChanges()));
    }
}
