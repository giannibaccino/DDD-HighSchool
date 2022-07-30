package com.highschool.domain.campus.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class CourseContent implements ValueObject<String> {

    private final String value;

    public CourseContent(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {return this.value;}
}
