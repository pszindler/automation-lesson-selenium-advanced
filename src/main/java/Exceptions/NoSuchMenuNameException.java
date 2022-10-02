package Exceptions;

import java.util.NoSuchElementException;

public class NoSuchMenuNameException extends NoSuchElementException {
    public NoSuchMenuNameException (String categoryName) {
        super(String.format("There is no category name: %s.", categoryName));
    }
}
