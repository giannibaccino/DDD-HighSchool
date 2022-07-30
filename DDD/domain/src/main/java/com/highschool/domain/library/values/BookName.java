package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BookName implements ValueObject<String> {

    private final String value;

    public BookName(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar en blanco");
        }
    }

    @Override
    public String value() { return this.value; }
}
