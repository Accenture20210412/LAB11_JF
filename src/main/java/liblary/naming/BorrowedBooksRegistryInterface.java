package liblary.naming;

public interface BorrowedBooksRegistryInterface {
    void borrow(Book book, Reader reader);

    boolean readerHasNoBookCopy(Book book, Reader reader);

    void returnBook(Book book, Reader reader);

    boolean readerHasBookCopy(Book book, Reader reader);
}
