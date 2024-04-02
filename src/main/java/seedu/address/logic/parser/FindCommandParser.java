package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InstrumentContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INSTRUMENT);

        String[] nameKeywords = new String[0];
        String[] instrumentKeywords = new String[0];

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_INSTRUMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INSTRUMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = ParserUtil.parseFindString(argMultimap.getValue(PREFIX_NAME).get(), PREFIX_NAME);
        }
        if (argMultimap.getValue(PREFIX_INSTRUMENT).isPresent()) {
            instrumentKeywords = ParserUtil.parseFindString(
                    argMultimap.getValue(PREFIX_INSTRUMENT).get(), PREFIX_INSTRUMENT);
        }

        return new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                new InstrumentContainsKeywordsPredicate(Arrays.asList(instrumentKeywords)));
    }

    /**
     * Returns true if at least one of the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
