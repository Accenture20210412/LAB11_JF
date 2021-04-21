package liblary.naming;

import java.util.List;

public interface ReadersManagerInterface {
    void enroll(Reader reader);

    List<Reader> readers();

    boolean contains(Reader reader);
}
