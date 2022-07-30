package com.highschool.domain.library.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.library.values.LibrarianFullName;
import com.highschool.domain.library.values.LibrarianID;

public class Librarian extends Entity<LibrarianID> {

    private LibrarianFullName librarianName;

    public Librarian(LibrarianID librarianID, LibrarianFullName librarianName) {
        super(librarianID);
        this.librarianName = librarianName;
    }

    //GETTERS
    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
