package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class TeacherFullName implements ValueObject<String> {

    private final String value;

    public TeacherFullName(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {return this.value;}
}
