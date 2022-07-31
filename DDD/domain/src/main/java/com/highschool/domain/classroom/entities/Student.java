package com.highschool.domain.classroom.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.classroom.values.StudentFullName;
import com.highschool.domain.classroom.values.StudentID;

import java.util.Objects;

public class Student extends Entity<StudentID> {

    private StudentFullName studentName;

    public Student(StudentID entityId, StudentFullName studentName) {
        super(entityId);
        this.studentName = studentName;
    }

    public void updateName(StudentFullName studentName) {
        this.studentName = Objects.requireNonNull(studentName);
    }

    public StudentFullName getStudentName() {
        return studentName;
    }
}
