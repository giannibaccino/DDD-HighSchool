package com.highschool.domain.campus.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class UserPassword implements ValueObject<String> {

    private final String value;

    public UserPassword(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("La contrasena no puede estar en blanco");
        }
    }

    @Override
    public String value() {return this.value;}
}
