package liblary.naming;


import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {
    LibraryFactory libraryFactory = new LibraryFactory();
    LibraryManager libraryManager;

    @BeforeEach
    void init() {
        libraryManager = libraryFactory.library();
    }

    @Test
    void shouldAddBookToLiblary() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);
        assertEquals(1, libraryManager.getBookAmounts(book));
    }

    @Test
    void shouldRegisterReader() {
        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);
        List<Reader> readerList = libraryManager.loadReaders();
        assertEquals(List.of(reader), readerList);
    }

    @Test
    void shouldNotBorrowBookNotInCatalogue() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);

        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertEquals(BorrowOutcome.NOT_IN_CATALOGUE, borrowOutcome);
    }

    @Test
    void shouldNotBorrowBookReaderNotEnrolled() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);
        Reader reader = new Reader("Jakub");

        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertEquals(BorrowOutcome.READER_NOT_ENROLLED, borrowOutcome);
    }

    @Test
    void shouldBorrowBook() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);

        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertEquals(BorrowOutcome.SUCCESS, borrowOutcome);
    }

    @Test
    void shouldNotBorrowBookNoAvailableCopies() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);

        Reader reader2 = new Reader("Kamil");
        libraryManager.newReader(reader2);

        libraryManager.provideBook(book, reader2);
        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertEquals(BorrowOutcome.NO_AVAILABLE_COPIES, borrowOutcome);
    }

    @Test
    void shouldValidAmountOfBooks() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);
        libraryManager.putBook(book);
        libraryManager.putBook(book);
        libraryManager.putBook(book);

        assertEquals(4, libraryManager.getBookAmounts(book));
    }
}