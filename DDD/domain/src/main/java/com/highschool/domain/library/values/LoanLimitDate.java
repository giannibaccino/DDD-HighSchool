package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class LoanLimitDate implements ValueObject<LocalDate> {

    private final LocalDate value;

    public LoanLimitDate(LocalDate value) { this.value = Objects.requireNonNull(value); }

    @Override
    public LocalDate value() {
        return this.value;
    }
}
