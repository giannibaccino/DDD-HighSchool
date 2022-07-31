package com.highschool.domain.library;

import co.com.sofka.domain.generic.EventChange;
import com.highschool.domain.library.entities.Book;
import com.highschool.domain.library.events.*;

import java.util.HashSet;

public class BookLoanChange extends EventChange {

    BookLoanChange(BookLoan loan) {
        apply((LoanCreated event) -> {
            loan.books = new HashSet<>();
            loan.reader = event.getReader();
            loan.librarian = event.getLibrarian();
            loan.loanStatus = event.getLoanStatus();
            loan.limitDate = event.getLimitDate();
        });

        apply((LoanStatusUpdated event) -> loan.loanStatus = event.getLoanStatus());

        apply((LoanLimitDateUpdated event) -> loan.limitDate = event.getLimitDate());

        apply((BookAdded event) -> {
            Book book = new Book(event.getBookID(), event.getBookName(), event.getBookDescription(), event.getBookCategory());
            loan.books.add(book);
        });

        apply((BookCategoryUpdated event) -> {
            Book book = loan.findBookById(event.getBookID()).orElseThrow();
            book.updateCategory(event.getBookCategory());
        });

        apply((ReaderNameUpdated event) -> loan.reader.updateName(event.getReaderName()));

        apply((LibrarianNameUpdated event) -> loan.librarian.updateName(event.getLibrarianName()));
    }
}
