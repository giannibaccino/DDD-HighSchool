package com.highschoolddd.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschoolddd.domain.library.values.LibrarianFullName;
import com.highschoolddd.domain.library.values.LibrarianID;

public class CreateLibrarian extends Command {

    private final LibrarianID librarianID;
    private final LibrarianFullName librarianName;

    public CreateLibrarian(LibrarianID librarianID, LibrarianFullName librarianName) {
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
