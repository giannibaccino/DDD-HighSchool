package com.highschool.domain.library.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.library.values.ReaderFullName;
import com.highschool.domain.library.values.ReaderID;

import java.util.Objects;

public class Reader extends Entity<ReaderID> {

    private ReaderFullName readerName;

    public Reader(ReaderID readerID, ReaderFullName readerName) {
        super(readerID);
        this.readerName = readerName;
    }

    //UPDATES
    public void updateName(ReaderFullName readerName) {
        this.readerName = Objects.requireNonNull(readerName);
    }

    //GETTERS
    public ReaderFullName getReaderName() {
        return readerName;
    }
}
