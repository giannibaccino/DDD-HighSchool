package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.Identity;

public class ClassID extends Identity {
    public ClassID() {}

    private ClassID(String id) { super(id); }

    public static ClassID of(String id) { return new ClassID(id); }
}
