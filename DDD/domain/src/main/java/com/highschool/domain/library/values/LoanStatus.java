package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class LoanStatus implements ValueObject<LoanStatusEnum> {

    private final LoanStatusEnum value;

    public LoanStatus(LoanStatusEnum value) { this.value = Objects.requireNonNull(value); }

    @Override
    public LoanStatusEnum value() {
        return this.value;
    }
}
