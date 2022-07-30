package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.values.BookID;
import com.highschool.domain.library.values.BookLoanID;
import com.highschool.domain.library.values.BookStatus;

public class LoanClosed extends DomainEvent {

    private final BookID bookID;
    private final BookStatus bookStatus;
    private final boolean wasClosed;

    public LoanClosed(BookID bookID, BookStatus bookStatus) {
        super("com.highschool.domain.library.loanclosed");
        this.bookID = bookID;
        this.bookStatus = bookStatus;
        wasClosed = true;
    }

    //GETTERS
    public BookID getBookID() {
        return bookID;
    }

    public boolean WasClosed() {

        return wasClosed;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
