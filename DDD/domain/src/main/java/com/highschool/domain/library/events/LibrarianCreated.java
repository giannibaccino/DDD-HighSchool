package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.LibrarianFullName;

public class LibrarianCreated extends DomainEvent {

    private final LibrarianFullName librarianName;

    public LibrarianCreated(LibrarianFullName librarianName) {
        super("com.highschool.domain.library.librariancreated");
        this.librarianName = librarianName;
    }

    //GETTERS
    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
