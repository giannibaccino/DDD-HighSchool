package com.highschoolddd.domain.library.values;

import co.com.sofka.domain.generic.Identity;

public class BookLoanID extends Identity {

    public BookLoanID() {}

    private BookLoanID(String id) { super(id); }

    public static BookLoanID of(String id) { return new BookLoanID(id); }
}
