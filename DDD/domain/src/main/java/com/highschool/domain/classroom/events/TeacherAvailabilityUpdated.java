package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.TeacherID;

public class TeacherAvailabilityUpdated extends DomainEvent {

    private final TeacherID teacherID;
    private final Availability availability;

    public TeacherAvailabilityUpdated(TeacherID teacherID, Availability availability) {
        super("com.highschool.domain.classroom.teacheravailabilityupdated");
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
