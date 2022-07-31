package com.highschool.domain.library.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.library.values.*;

import java.util.Objects;

public class Book extends Entity<BookID> {

    private final BookName bookName;
    private final BookDescription bookDescription;
    private BookCategory bookCategory;

    public Book(BookID entityId, BookName bookName, BookDescription bookDescription, BookCategory bookCategory) {
        super(entityId);
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
    }

    //UPDATES
    public void updateCategory(BookCategory bookCategory) {
        this.bookCategory = Objects.requireNonNull(bookCategory);
    }

    //GETTERS
    public BookName getBookName() {
        return bookName;
    }

    public BookDescription getBookDescription() {
        return bookDescription;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }
}
