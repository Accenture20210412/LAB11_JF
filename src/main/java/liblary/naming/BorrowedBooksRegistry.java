package liblary.naming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasa zapisuje listę czytelników i ich wypożyczone książki
 */
class BorrowedBooksRegistry implements BorrowedBooksRegistryInterface {
    private final Map<Reader, List<ISBN>> rentals;

    BorrowedBooksRegistry() {
        rentals = new HashMap<>();
    }
@Override
public void borrow(Book book, Reader reader) {
        if (rentals.containsKey(reader)) {
            rentals.get(reader).add(book.getIsbn());
        } else {
            List<ISBN> books = new ArrayList<>();
            books.add(book.getIsbn());
            rentals.put(reader, books);
        }
    }
@Override
public boolean readerHasNoBookCopy(Book book, Reader reader) {
        return !readerHasBookCopy(book, reader);
    }
@Override
public void returnBook(Book book, Reader reader) {
        ISBN isbn = book.getIsbn();
        rentals.get(reader).remove(isbn);
    }
@Override
public boolean readerHasBookCopy(Book book, Reader reader) {
        ISBN isbn = book.getIsbn();
        return rentals.containsKey(reader) && rentals.get(reader).contains(isbn);
    }
}
