package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.StudentFullName;
import com.highschool.domain.classroom.values.StudentID;

public class UpdateStudentName extends Command {

    private final ClassID classID;
    private final StudentID studentID;
    private final StudentFullName studentName;

    public UpdateStudentName(ClassID classID, StudentID studentID, StudentFullName studentName) {
        this.classID = classID;
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public ClassID getClassID() {
        return classID;
    }

    public StudentID getStudentID() {
        return studentID;
    }

    public StudentFullName getStudentName() {
        return studentName;
    }
}
