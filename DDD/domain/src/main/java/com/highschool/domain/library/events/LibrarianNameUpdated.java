package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.LibrarianFullName;
import com.highschool.domain.library.values.LibrarianID;

public class LibrarianNameUpdated extends DomainEvent {

    private final LibrarianID librarianID;
    private final LibrarianFullName librarianName;

    public LibrarianNameUpdated(LibrarianID librarianID, LibrarianFullName librarianName) {
        super("com.highschool.domain.library.librariannameupdated");
        this.librarianID = librarianID;
        this.librarianName = librarianName;
    }

    //GETTERS

    public LibrarianID getLibrarianID() {
        return librarianID;
    }

    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
