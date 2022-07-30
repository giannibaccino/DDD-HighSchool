package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.Identity;

public class ReaderID extends Identity {

    public ReaderID() {}

    private ReaderID(String id) {super(id);}

    public static ReaderID of(String id) { return new ReaderID(id); }
}
