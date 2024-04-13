package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    /**
     * Constructs a CommandException with the specified message.
     *
     * @param message The message to be stored in the CommandException.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a CommandException with the specified message and cause.
     *
     * @param message The message to be stored in the CommandException.
     * @param cause The cause of the CommandException.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
