package com.highschoolddd.domain.library.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BookStatus implements ValueObject<BookStatusEnum> {

    private final BookStatusEnum value;

    public BookStatus(BookStatusEnum value) { this.value = Objects.requireNonNull(value); }

    @Override
    public BookStatusEnum value() { return this.value; }
}
