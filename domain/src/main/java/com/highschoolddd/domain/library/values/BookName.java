package com.highschoolddd.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BookName implements ValueObject<String> {

    private final String value;

    public BookName(String value) { this.value = Objects.requireNonNull(value); }

    @Override
    public String value() { return this.value; }
}
