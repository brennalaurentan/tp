package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * Constructs a ParseException with the specified detail message.
     *
     * @param message The detail message to be stored in the ParseException.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Constructs a ParseException with the specified detail message and cause.
     *
     * @param message The detail message to be stored in the ParseException.
     * @param cause The cause of the ParseException.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
