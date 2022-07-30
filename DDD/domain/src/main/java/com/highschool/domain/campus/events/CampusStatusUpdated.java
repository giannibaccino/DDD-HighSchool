package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CampusStatus;
import com.highschool.domain.campus.values.CampusURL;

public class CampusStatusUpdated extends DomainEvent {

    private final CampusURL campusURL;
    private final CampusStatus campusStatus;

    public CampusStatusUpdated(CampusURL campusURL, CampusStatus campusStatus) {
        super("com.highschool.domain.campus.campusstateupdated");
        this.campusURL = campusURL;
        this.campusStatus = campusStatus;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CampusStatus getCampusStatus() {
        return campusStatus;
    }
}
