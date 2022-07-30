package com.highschoolddd.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschoolddd.domain.library.entities.Book;
import com.highschoolddd.domain.library.entities.Librarian;
import com.highschoolddd.domain.library.entities.Reader;
import com.highschoolddd.domain.library.values.LoanLimitDate;

public class LoanCreated extends DomainEvent {

    private final Book book;
    private final Reader reader;
    private final Librarian librarian;
    private final LoanLimitDate limitDate;

    public LoanCreated(Book book, Reader reader, Librarian librarian, LoanLimitDate limitDate) {
        super("com.highschoolddd.domain.library.loancreated");
        this.book = book;
        this.reader = reader;
        this.librarian = librarian;
        this.limitDate = limitDate;
    }

    //GETTERS
    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public LoanLimitDate getLimitDate() {
        return limitDate;
    }
}
