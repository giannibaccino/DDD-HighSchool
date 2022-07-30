package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.BookCategory;
import com.highschool.domain.library.values.BookDescription;
import com.highschool.domain.library.values.BookID;
import com.highschool.domain.library.values.BookName;

public class BookAdded extends DomainEvent {

    private final BookID bookID;
    private final BookName bookName;
    private final BookDescription bookDescription;
    private final BookCategory bookCategory;

    public BookAdded(BookID bookID, BookName bookName, BookDescription bookDescription, BookCategory bookCategory) {
        super("com.highschool.domain.library.bookadded");
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
    }

    //GETTERS

    public BookID getBookID() {
        return bookID;
    }
    public BookName getBookName() { return bookName; }

    public BookDescription getBookDescription() { return bookDescription; }

    public BookCategory getBookCategory() { return bookCategory; }
}
