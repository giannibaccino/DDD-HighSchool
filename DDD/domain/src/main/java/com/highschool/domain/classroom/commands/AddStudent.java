package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.StudentFullName;

public class AddStudent extends Command {

    private final ClassID classID;
    private final StudentFullName studentName;

    public AddStudent(ClassID classID, StudentFullName studentName) {
        this.classID = classID;
        this.studentName = studentName;
    }

    public ClassID getClassID() {
        return classID;
    }

    public StudentFullName getStudentName() {
        return studentName;
    }
}
