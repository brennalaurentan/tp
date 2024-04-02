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
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
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
     * Creates a FindCommand to find persons with the specified {@code NameContainsKeywordsPredicate}
     * and/or {@code InstrumentContainsKeywordsPredicate}.
     */
    public FindCommand(
            NameContainsKeywordsPredicate namePredicate,
            InstrumentContainsKeywordsPredicate instrumentPredicate) {
        this.namePredicate = namePredicate;
        this.instrumentPredicate = instrumentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.or(instrumentPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .add("instrumentPredicate", instrumentPredicate)
                .toString();
    }
}
