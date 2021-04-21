package liblary.naming;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class ReadersManager implements ReadersManagerInterface {
    private final List<Reader> readers;

    ReadersManager() {
        readers = new ArrayList<>();
    }

@Override
public void enroll(Reader reader) {
        readers.add(reader);
    }
@Override
public List<Reader> readers() {
        return unmodifiableList(readers);
    }
@Override
public boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}