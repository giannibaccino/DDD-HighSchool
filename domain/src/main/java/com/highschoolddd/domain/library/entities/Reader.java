package com.highschoolddd.domain.library.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschoolddd.domain.library.values.ReaderFullName;
import com.highschoolddd.domain.library.values.ReaderID;

public class Reader extends Entity<ReaderID> {

    private ReaderFullName readerName;

    public Reader(ReaderID readerID, ReaderFullName readerName) {
        super(readerID);
        this.readerName = readerName;
    }

    //GETTERS
    public ReaderFullName getReaderName() {
        return readerName;
    }
}
