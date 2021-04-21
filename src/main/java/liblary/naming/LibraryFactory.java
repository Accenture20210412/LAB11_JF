package liblary.naming;

class LibraryFactory {
    LibraryManager library() {
        ReadersManager readersManager = new ReadersManager();
        LibraryResources libraryResources = new LibraryResources(new BookCatalogue(), new BookWarehouse());
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        BorrowManager borrowManager = new BorrowManager(libraryResources, readersManager, borrowedBookRegistry);
        return new LibraryManager(readersManager, borrowManager, libraryResources);
    }
}
