package com.highschoolddd.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschoolddd.domain.library.values.ReaderFullName;

public class ReaderCreated extends DomainEvent {

    private final ReaderFullName readerName;

    public ReaderCreated(ReaderFullName readerName) {
        super("com.highschoolddd.domain.library.readercreated");
        this.readerName = readerName;
    }

    //GETTERS
    public ReaderFullName getReaderName() {
        return readerName;
    }
}
