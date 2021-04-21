package liblary.naming;

import java.util.Set;

// TODO: class cleanup

class LibraryResources implements Resources {
    private final BookCatalogue catalogue;
    private final BookWarehouse bookWarehouse;

    LibraryResources(BookCatalogue catalogue, BookWarehouse bookWarehouse) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
    }

    void addBook(Book book) {
        catalogue.addBook(book);
        bookWarehouse.addBook(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.addBook(book);
        bookWarehouse.addBooks(book.getIsbn(), amount);
    }
@Override
    public int availableCopies(Book book) {
        return bookWarehouse.availableCopies(book.getIsbn());
    }

    Set<Book> bookCatalogue() {
        return catalogue.getUniqueBooks();
    }

    public void take(ISBN isbn) {
        bookWarehouse.borrowBook(isbn);
    }
@Override
    public boolean contains(Book book) {
        return catalogue.isContains(book);
    }
@Override
    public void addToResources(ISBN isbn) {
        bookWarehouse.addBook(isbn);
    }
}