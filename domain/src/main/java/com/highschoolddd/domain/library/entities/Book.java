package com.highschoolddd.domain.library.entities;

import co.com.sofka.domain.generic.Entity;

import java.util.Objects;

public class Book extends Entity<BookID> {

    private BookName bookName;
    private BookDescription bookDescription;
    private BookCategory bookCategory;
    private BookStatus bookStatus;

    public Book(BookID bookID, BookName bookName, BookDescription bookDescription, BookCategory bookCategory, BookStatus bookStatus) {
        super(bookID);
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.bookStatus = bookStatus;
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    //UPDATES
    public void updateBookStatus(BookStatus bookStatus) { this.bookStatus = Objects.requireNonNull(bookStatus) ; }
}
