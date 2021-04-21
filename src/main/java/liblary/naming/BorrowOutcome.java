package liblary.naming;

enum BorrowOutcome {
    NOT_IN_CATALOGUE,
    READER_NOT_ENROLLED,
    NO_AVAILABLE_COPIES,
    BOOK_ALREADY_BORROWED_BY_READER,
    SUCCESS
}
