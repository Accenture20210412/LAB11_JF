package liblary.naming;

public interface Resources {
    void take(ISBN isbn);
    void addToResources(ISBN isbn);
    int availableCopies(Book book);
    boolean contains(Book book);
}
