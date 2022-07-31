package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.TeacherFullName;
import com.highschool.domain.classroom.values.TeacherID;

public class UpdateTeacherName extends Command {

    private final ClassID classID;
    private final TeacherID teacherID;
    private final TeacherFullName teacherName;

    public UpdateTeacherName(ClassID classID, TeacherID teacherID, TeacherFullName teacherName) {
        this.classID = classID;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
    }

    public ClassID getClassID() {
        return classID;
    }

    public TeacherID getTeacherID() {
        return teacherID;
    }

    public TeacherFullName getTeacherName() {
        return teacherName;
    }
}
