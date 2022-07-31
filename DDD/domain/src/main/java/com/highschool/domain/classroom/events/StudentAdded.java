package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.StudentFullName;
import com.highschool.domain.classroom.values.StudentID;

public class StudentAdded extends DomainEvent {

    private final StudentID studentID;
    private final StudentFullName studentName;

    public StudentAdded(StudentID studentID, StudentFullName studentName) {
        super("com.highschool.domain.classroom.studentadded");
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
