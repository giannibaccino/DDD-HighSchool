package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class TestDate implements ValueObject<LocalDate> {

    private final LocalDate value;

    public TestDate(LocalDate value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public LocalDate value() {return this.value;}
}
