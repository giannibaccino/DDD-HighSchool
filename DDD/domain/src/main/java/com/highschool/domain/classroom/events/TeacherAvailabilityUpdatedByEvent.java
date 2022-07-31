package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.TeacherID;

public class TeacherAvailabilityUpdatedByEvent extends DomainEvent {

    private final TeacherID teacherID;
    private final Availability availability;

    public TeacherAvailabilityUpdatedByEvent(TeacherID teacherID, Availability availability) {
        super("com.highschool.domain.classroom.teacheravailabilityupdatedbyevent");
        this.teacherID = teacherID;
        this.availability = availability;
    }

    public TeacherID getTeacherID() {
        return teacherID;
    }

    public Availability getAvailability() {
        return availability;
    }
}
