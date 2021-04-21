package liblary.naming;

import java.util.Set;

/**
 * Wzorzec projektowy FACADE do klasy ksiązka
 */
public interface BookFacade {
    void putBook(Book book);

    void addBook(Book book, int amount);

    int getBookAmounts(Book book);

    Set<Book> getBooks();

    BorrowOutcome provideBook(Book book, Reader reader);

    ReturnOutcome returns(Book book, Reader reader);
}
