package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.BookCategory;
import com.highschool.domain.library.values.BookID;

public class BookCategoryUpdated extends DomainEvent {

    private final BookID bookID;
    private final BookCategory bookCategory;

    public BookCategoryUpdated(BookID bookID, BookCategory bookCategory) {
        super("com.highschool.domain.library.bookcategoryupdated");
        this.bookID = bookID;
        this.bookCategory = bookCategory;
    }

    //GETTERS

    public BookID getBookID() {
        return bookID;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }
}
