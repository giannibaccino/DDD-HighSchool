package com.highschool.domain.classroom.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Availability implements ValueObject<AvailabilityEnum> {

    private final AvailabilityEnum value;

    public Availability(AvailabilityEnum value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public AvailabilityEnum value() {return this.value;}
}
