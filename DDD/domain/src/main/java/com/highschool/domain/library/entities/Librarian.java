package com.highschool.domain.library.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.library.values.LibrarianFullName;
import com.highschool.domain.library.values.LibrarianID;

import java.util.Objects;

public class Librarian extends Entity<LibrarianID> {

    private LibrarianFullName librarianName;

    public Librarian(LibrarianID librarianID, LibrarianFullName librarianName) {
        super(librarianID);
        this.librarianName = librarianName;
    }

    //UPDATES
    public void updateName(LibrarianFullName librarianName) {
        this.librarianName = Objects.requireNonNull(librarianName);
    }

    //GETTERS
    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
