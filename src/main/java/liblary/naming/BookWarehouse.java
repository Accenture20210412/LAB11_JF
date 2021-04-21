package liblary.naming;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa krzedstawiająca zaopatrzenie/stan magazynu w bibliotece
 * implementuje interface BookFacade
 * implementuje metody zarządzające biblioteką
 */

class BookWarehouse {
    private final Map<ISBN, Integer> bookWarehouse;

    BookWarehouse() {
        bookWarehouse = new HashMap<>();
    }

    void addBook(ISBN isbn) {
        bookWarehouse.merge(isbn, 1, Integer::sum);
    }

    void addBooks(ISBN isbn, int amount) {
        bookWarehouse.merge(isbn, amount, Integer::sum);
    }

    int availableCopies(ISBN isbn) {
        return bookWarehouse.getOrDefault(isbn, 0);
    }

    void borrowBook(ISBN isbn) {
        bookWarehouse.put(isbn, bookWarehouse.get(isbn) - 1);
    }

//    public Map<ISBN, Integer> getBookStore() {
//        return bookStore;
//    }
}
