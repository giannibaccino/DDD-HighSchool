package com.highschool.domain.library.values;

import co.com.sofka.domain.generic.Identity;

public class BookID extends Identity {

    public BookID() {}

    private BookID(String id) { super(id); }

    public static BookID of(String id) { return new BookID(id); }
}
