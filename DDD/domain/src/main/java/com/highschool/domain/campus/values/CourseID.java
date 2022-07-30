package com.highschool.domain.campus.values;

import co.com.sofka.domain.generic.Identity;

public class CourseID extends Identity {

    public CourseID() {}

    private CourseID(String id) {super(id);}

    public static CourseID of(String id) {return new CourseID(id);}
}
