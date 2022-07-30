package com.highschoolddd.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class ReaderFullName implements ValueObject<String> {

    private final String firstName;
    private final String lastName;

    public ReaderFullName(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    @Override
    public String value() { return this.firstName + " " + this.lastName; }
}
