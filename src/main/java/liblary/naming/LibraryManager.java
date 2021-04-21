package liblary.naming;

import java.util.List;
import java.util.Set;

public class LibraryManager implements BookFacade, ReaderFacade {
    private final ReadersManagerInterface readersManager;
    private final BorrowManager borrowManager;
    private final LibraryResources libraryResources;

    LibraryManager(ReadersManager readersManager, BorrowManager borrowManager, LibraryResources libraryResources) {
        this.readersManager = readersManager;
        this.borrowManager = borrowManager;
        this.libraryResources = libraryResources;
    }

    @Override
    public void putBook(Book book) {
        libraryResources.addBook(book);
    }

    @Override
    public void addBook(Book book, int howMany) {
        libraryResources.addBooks(book, howMany);
    }

    @Override
    public int getBookAmounts(Book book) {
        return libraryResources.availableCopies(book);
    }

    @Override
    public Set<Book> getBooks() {
        return libraryResources.bookCatalogue();
    }

    @Override
    public void newReader(Reader reader) {
        readersManager.enroll(reader);
    }

    @Override
    public List<Reader> loadReaders() {
        return readersManager.readers();
    }

    @Override
    public BorrowOutcome provideBook(Book what, Reader who) {
        return borrowManager.borrowBook(what, who);
    }

    @Override
    public ReturnOutcome returns(Book book, Reader who) {
        return borrowManager.returnBook(book, who);
    }
}
