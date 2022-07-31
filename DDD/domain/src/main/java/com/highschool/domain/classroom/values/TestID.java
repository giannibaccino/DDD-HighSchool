package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.Identity;

public class TestID extends Identity {

    public TestID() {}

    private TestID(String id) { super(id); }

    public static TestID of(String id) { return new TestID(id); }
}
