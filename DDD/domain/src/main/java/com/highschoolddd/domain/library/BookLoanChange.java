package com.highschoolddd.domain.library;

import co.com.sofka.domain.generic.EventChange;
import com.highschoolddd.domain.library.events.LoanCreated;

public class BookLoanChange extends EventChange {

    BookLoanChange(BookLoan loan) {
        apply((LoanCreated event) -> {
            loan.book = event.getBook();
            loan.reader = event.getReader();
            loan.librarian = event.getLibrarian();
            loan.limitDate = event.getLimitDate();
        });
    }
}
