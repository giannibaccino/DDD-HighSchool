package com.highschoolddd.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschoolddd.domain.library.values.LibrarianFullName;

public class LibrarianCreated extends DomainEvent {

    private final LibrarianFullName librarianName;

    public LibrarianCreated(LibrarianFullName librarianName) {
        super("com.highschoolddd.domain.library.librariancreated");
        this.librarianName = librarianName;
    }

    //GETTERS
    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
