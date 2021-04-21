package liblary.naming;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Klasa zawiera zestaw unikalnych książek
 * oraz metody do zarządzania setem
 */
class BookCatalogue {
    private final Set<Book> catalogue;

    BookCatalogue() {
        catalogue = new HashSet<>();
    }

    void addBook(Book book) {
        catalogue.add(book);
    }

    Set<Book> getUniqueBooks() {
        return unmodifiableSet(catalogue);
    }

    boolean isContains(Book book) {
        return catalogue.contains(book);
    }

}
