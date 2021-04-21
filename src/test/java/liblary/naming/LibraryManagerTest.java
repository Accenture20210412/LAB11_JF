package liblary.naming;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @Test
    void shouldBorrowBookMoreCopiesInCatalogue() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);
        libraryManager.putBook(book);
        libraryManager.putBook(book);
        libraryManager.putBook(book);

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);

        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertTrue(() ->
                libraryManager.getBookAmounts(book) == 3 &&
                borrowOutcome == BorrowOutcome.SUCCESS);
    }

    @Test
    void shouldNotBorrowBookAlreadyBorrowedByReader() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);


        libraryManager.provideBook(book, reader);
        BorrowOutcome borrowOutcome = libraryManager.provideBook(book, reader);
        assertEquals(BorrowOutcome.BOOK_ALREADY_BORROWED_BY_READER, borrowOutcome);
    }

    @Test
    void shouldReturnBook() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        libraryManager.putBook(book);

        Reader reader = new Reader("Jakub");
        libraryManager.newReader(reader);
        libraryManager.provideBook(book, reader);

        ReturnOutcome returnOutcome = libraryManager.returns(book, reader);
        assertEquals(ReturnOutcome.success, returnOutcome);
    }

    @Test
    void shouldNotReturnBookReaderNotEnrolled() {
        Book book = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        Reader reader = new Reader("Jakub");
        libraryManager.putBook(book);

        ReturnOutcome returnOutcome = libraryManager.returns(book, reader);
        assertEquals(ReturnOutcome.readerNotEnrolled, returnOutcome);
    }

    @Test
    @Disabled
    void shouldNotReturnBookNotBorrowedByReader() {
        Book book1 = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        Book book2 = new Book( ISBN.of("1234")
                , "Juliusz Słowacki"
                , "Balladyna");
        Reader reader1 = new Reader("Jakub");
        Reader reader2 = new Reader("Krzysztof");

        libraryManager.putBook(book1);
        libraryManager.putBook(book2);
        libraryManager.newReader(reader1);
        libraryManager.newReader(reader2);

        libraryManager.provideBook(book1, reader1);
        libraryManager.provideBook(book2, reader2);

        ReturnOutcome returnOutcome = libraryManager.returns(book1, reader2);
        assertEquals(ReturnOutcome.bookNotBorrowedByReader, returnOutcome);
    }

    @Test
    void shouldBorrowTwoDifrentBooks() {
        Book book1 = new Book( ISBN.of("1234")
                , "Aleksander Kamiński"
                , "Kamienie na szaniec");
        Book book2 = new Book( ISBN.of("4321")
                , "Juliusz Słowacki"
                , "Balladyna");
        Reader reader = new Reader("Jakub");

        libraryManager.putBook(book1);
        libraryManager.putBook(book2);
        libraryManager.newReader(reader);

        BorrowOutcome outcome1 = libraryManager.provideBook(book1, reader);
        BorrowOutcome outcome2 = libraryManager.provideBook(book2, reader);

        assertTrue(() ->
                BorrowOutcome.SUCCESS.equals(outcome1) &&
                BorrowOutcome.SUCCESS.equals(outcome2));
    }
}