package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.entities.Teacher;

public class ClassCreated extends DomainEvent {

    private final Teacher teacher;

    public ClassCreated(Teacher teacher) {
        super("com.highschool.domain.classroom.classcreated");
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
