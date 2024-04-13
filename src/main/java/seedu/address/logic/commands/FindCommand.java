package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.InstrumentContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Represents a command that finds and lists all persons in address book whose name and/or instrument contains any
 * of the argument keywords. Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose specified contact detail "
            + "field contains any of the specified keywords (case-insensitive) and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME_KEYWORD [MORE_KEYWORDS] and/or "
            + PREFIX_INSTRUMENT + "INSTRUMENT_KEYWORD [MORE_KEYWORDS] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INSTRUMENT + "clarinet flute\n";;

    private final NameContainsKeywordsPredicate namePredicate;
    private final InstrumentContainsKeywordsPredicate instrumentPredicate;

    /**
     * Constructs a FindCommand to find persons with the specified name keywords and/or instrument keywords.
     *
     * @param namePredicate Keywords to filter persons by name.
     * @param instrumentPredicate Keywords to filter persons by instrument.
     */
    public FindCommand(
            NameContainsKeywordsPredicate namePredicate,
            InstrumentContainsKeywordsPredicate instrumentPredicate) {
        this.namePredicate = namePredicate;
        this.instrumentPredicate = instrumentPredicate;
    }

    /**
     * Executes the command to find and list all persons in address book whose name and/or instrument contains
     * any of the argument keywords.
     *
     * @param model The model which the command should operate on.
     * @return A command result with all persons whose name and/or instrument contains any of the argument keywords.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.or(instrumentPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks if the FindCommand is equal to the other object.
     *
     * @param other The other object to compare.
     * @return True if the FindCommand is equal to the other object.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return namePredicate.equals(otherFindCommand.namePredicate)
                || instrumentPredicate.equals(otherFindCommand.instrumentPredicate);
    }

    /**
     * Returns a string representation of the FindCommand.
     *
     * @return String representation of the FindCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .add("instrumentPredicate", instrumentPredicate)
                .toString();
    }
}
