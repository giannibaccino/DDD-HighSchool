package com.highschool.domain.classroom.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.TeacherFullName;
import com.highschool.domain.classroom.values.TeacherID;

import java.util.Objects;

public class Teacher extends Entity<TeacherID> {

    private TeacherFullName teacherName;
    private Availability availability;

    public Teacher(TeacherID teacherID, TeacherFullName teacherName, Availability availability) {
        super(teacherID);
        this.teacherName = teacherName;
        this.availability = availability;
    }

    public void updateName(TeacherFullName teacherName) {
        this.teacherName = Objects.requireNonNull(teacherName);
    }

    public void updateAvailability(Availability availability) {
        this.availability = Objects.requireNonNull(availability);
    }

    public TeacherFullName getTeacherName() {
        return teacherName;
    }

    public Availability getAvailability() {
        return availability;
    }
}
