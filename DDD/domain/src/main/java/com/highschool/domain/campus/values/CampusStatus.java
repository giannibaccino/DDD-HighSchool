package com.highschool.domain.campus.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class CampusStatus implements ValueObject<CampusStatusEnum> {

    private final CampusStatusEnum value;

    public CampusStatus(CampusStatusEnum value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public CampusStatusEnum value() {return this.value;}
}
