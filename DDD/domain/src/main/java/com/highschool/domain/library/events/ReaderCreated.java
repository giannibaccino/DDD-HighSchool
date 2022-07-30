package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.ReaderFullName;

public class ReaderCreated extends DomainEvent {

    private final ReaderFullName readerName;

    public ReaderCreated(ReaderFullName readerName) {
        super("com.highschool.domain.library.readercreated");
        this.readerName = readerName;
    }

    //GETTERS
    public ReaderFullName getReaderName() {
        return readerName;
    }
}
