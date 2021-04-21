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
}