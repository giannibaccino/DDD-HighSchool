package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.StudentFullName;
import com.highschool.domain.classroom.values.StudentID;

public class StudentNameUpdated extends DomainEvent {

    private final StudentID studentID;
    private final StudentFullName studentName;

    public StudentNameUpdated(StudentID studentID, StudentFullName studentName) {
        super("com.highschool.domain.classroom.studentnameupdated");
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public StudentID getStudentID() {
        return studentID;
    }

    public StudentFullName getStudentName() {
        return studentName;
    }
}
