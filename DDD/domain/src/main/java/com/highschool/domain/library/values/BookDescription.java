package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BookDescription implements ValueObject<String> {

    private final String value;

    public BookDescription(String value) { this.value = Objects.requireNonNull(value); }

    @Override
    public String value() { return this.value; }
}
