package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.LibrarianFullName;
import com.highschool.domain.library.values.LibrarianID;

public class UpdateLibrarianName extends Command {

    private final BookLoanID loanID;
    private final LibrarianID librarianID;
    private final LibrarianFullName librarianName;

    public UpdateLibrarianName(BookLoanID loanID, LibrarianID librarianID, LibrarianFullName librarianName) {
        this.loanID = loanID;
        this.librarianID = librarianID;
        this.librarianName = librarianName;
    }

    //GETTERS

    public BookLoanID getLoanID() {
        return loanID;
    }

    public LibrarianID getLibrarianID() {
        return librarianID;
    }

    public LibrarianFullName getLibrarianName() {
        return librarianName;
    }
}
