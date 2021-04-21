package liblary.naming;

import java.util.Objects;

/**
 * Klasa przedstawiająca obiekt typu książka.
 */

class Book {
    private final ISBN isbn;
    private final String author;
    private final String title;

    Book(ISBN isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    ISBN getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
