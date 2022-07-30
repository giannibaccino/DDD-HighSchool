package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.ReaderFullName;
import com.highschool.domain.library.values.ReaderID;

public class ReaderNameUpdated extends DomainEvent {

    private final ReaderID readerID;
    private final ReaderFullName readerName;

    public ReaderNameUpdated(ReaderID readerID, ReaderFullName readerName) {
        super("com.highschool.domain.library.readernameupdated");
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
