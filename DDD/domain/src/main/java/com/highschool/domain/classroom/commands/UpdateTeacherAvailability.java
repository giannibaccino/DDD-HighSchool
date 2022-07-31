package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.TeacherID;

public class UpdateTeacherAvailability extends Command {

    private final ClassID classID;
    private final TeacherID teacherID;
    private final Availability availability;

    public UpdateTeacherAvailability(ClassID classID, TeacherID teacherID, Availability availability) {
        this.classID = classID;
        this.teacherID = teacherID;
        this.availability = availability;
    }

    public ClassID getClassID() {
        return classID;
    }

    public TeacherID getTeacherID() {
        return teacherID;
    }

    public Availability getAvailability() {
        return availability;
    }
}
