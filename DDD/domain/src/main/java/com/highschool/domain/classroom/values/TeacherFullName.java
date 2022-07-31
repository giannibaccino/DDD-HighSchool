package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class TeacherFullName implements ValueObject<String> {

    private final String firstName;
    private final String lastName;

    public TeacherFullName(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName);
        if(this.firstName.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar en blanco");
        }
        this.lastName = Objects.requireNonNull(lastName);
        if(this.lastName.isBlank()){
            throw new IllegalArgumentException("El apellido no puede estar en blanco");
        }
    }

    @Override
    public String value() { return this.firstName + " " + this.lastName; }
}
