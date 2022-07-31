package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.TeacherFullName;
import com.highschool.domain.classroom.values.TeacherID;

public class TeacherNameUpdated extends DomainEvent {

    private final TeacherID teacherID;
    private final TeacherFullName teacherName;

    public TeacherNameUpdated(TeacherID teacherID, TeacherFullName teacherName) {
        super("com.highschool.domain.classroom.teachernameupdated");
        this.teacherID = teacherID;
        this.teacherName = teacherName;
    }

    public TeacherID getTeacherID() {
        return teacherID;
    }

    public TeacherFullName getTeacherName() {
        return teacherName;
    }
}
