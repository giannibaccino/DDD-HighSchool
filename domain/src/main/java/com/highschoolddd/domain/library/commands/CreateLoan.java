package com.highschoolddd.domain.library.commands;

import co.com.sofka.domain.generic.Command;
import com.highschoolddd.domain.library.entities.Book;
import com.highschoolddd.domain.library.entities.Librarian;
import com.highschoolddd.domain.library.entities.Reader;
import com.highschoolddd.domain.library.values.BookLoanID;
import com.highschoolddd.domain.library.values.LoanLimitDate;

public class CreateLoan extends Command {

    private final BookLoanID loanID;
    private final Book book;
    private final Reader reader;
    private final Librarian librarian;
    private final LoanLimitDate limitDate;

    public CreateLoan(BookLoanID loanID, Book book, Reader reader, Librarian librarian, LoanLimitDate limitDate) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.librarian = librarian;
        this.limitDate = limitDate;
    }

    //GETTERS
    public BookLoanID getLoanID() {
        return loanID;
    }

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