package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.Identity;

public class TeacherID extends Identity {

    public TeacherID() {}

    private TeacherID(String id) { super(id); }

    public static TeacherID of(String id) { return new TeacherID(id); }
}
