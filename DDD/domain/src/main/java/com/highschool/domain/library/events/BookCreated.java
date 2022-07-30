package com.highschool.domain.library.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.library.values.BookCategory;
import com.highschool.domain.library.values.BookDescription;
import com.highschool.domain.library.values.BookName;
import com.highschool.domain.library.values.BookStatus;

public class BookCreated extends DomainEvent {

    private final BookName bookName;
    private final BookDescription bookDescription;
    private final BookCategory bookCategory;
    private final BookStatus bookStatus;

    public BookCreated(BookName bookName, BookDescription bookDescription, BookCategory bookCategory, BookStatus bookStatus) {
        super("com.highschool.domain.library.bookcreated");
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.bookStatus = bookStatus;
    }

    //GETTERS
    public BookName getBookName() { return bookName; }

    public BookDescription getBookDescription() { return bookDescription; }

    public BookCategory getBookCategory() { return bookCategory; }

    public BookStatus getBookStatus() { return bookStatus; }
}
