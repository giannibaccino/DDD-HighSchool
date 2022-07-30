package com.highschoolddd.domain.library.values;

import co.com.sofka.domain.generic.Identity;

public class LibrarianID extends Identity {

    public LibrarianID() {}

    private LibrarianID(String id) { super(id); }

    public static  LibrarianID of(String id) { return new LibrarianID(id); }
}
