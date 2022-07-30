package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CampusStatus;

public class CampusCreated extends DomainEvent {

    private final CampusStatus campusStatus;

    public CampusCreated(CampusStatus campusStatus) {
        super("com.highschool.domain.campus.campuscreated");
        this.campusStatus = campusStatus;
    }

    public CampusStatus getCampusStatus() {
        return campusStatus;
    }
}
