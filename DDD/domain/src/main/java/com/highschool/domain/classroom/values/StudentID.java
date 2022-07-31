package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.Identity;

public class StudentID extends Identity {

    public StudentID() {}

    private StudentID(String id) { super(id); }

    public static StudentID of(String id) { return new StudentID(id); }
}
