package com.highschool.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.library.values.*;

public class CreateBook extends Command {

    private final BookID bookID;
    private final BookName bookName;
    private final BookDescription bookDescription;
    private final BookCategory bookCategory;
    private final BookStatus bookStatus;

    public CreateBook(BookID bookID, BookName bookName, BookDescription bookDescription, BookCategory bookCategory, BookStatus bookStatus) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.bookStatus = bookStatus;
    }

    //GETTERS
    public BookID getBookID() {
        return bookID;
    }

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
}
