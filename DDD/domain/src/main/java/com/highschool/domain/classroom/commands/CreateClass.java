package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.values.ClassID;

public class CreateClass extends Command {

    private final ClassID classID;
    private final Teacher teacher;

    public CreateClass(ClassID classID, Teacher teacher) {
        this.classID = classID;
        this.teacher = teacher;
    }

    public ClassID getClassID() {
        return classID;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
