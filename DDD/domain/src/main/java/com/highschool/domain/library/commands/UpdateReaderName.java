package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.ReaderFullName;
import com.highschool.domain.library.values.ReaderID;

public class UpdateReaderName extends Command {

    private final BookLoanID loanID;
    private final ReaderID readerID;
    private final ReaderFullName readerName;

    public UpdateReaderName(BookLoanID loanID, ReaderID readerID, ReaderFullName readerName) {
        this.loanID = loanID;
        this.readerID = readerID;
        this.readerName = readerName;
    }

    //GETTERS

    public BookLoanID getLoanID() {
        return loanID;
    }

    public ReaderID getReaderID() {
        return readerID;
    }

    public ReaderFullName getReaderName() {
        return readerName;
    }
}
