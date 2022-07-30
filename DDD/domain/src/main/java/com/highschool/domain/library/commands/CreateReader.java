package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.ReaderFullName;
import com.highschool.domain.library.values.ReaderID;

public class CreateReader extends Command {

    private final ReaderID readerID;
    private final ReaderFullName readerName;

    public CreateReader(ReaderID readerID, ReaderFullName readerName) {
        this.readerID = readerID;
        this.readerName = readerName;
    }

    //GETTERS
    public ReaderID getReaderID() {
        return readerID;
    }

    public ReaderFullName getReaderName() {
        return readerName;
    }
}
