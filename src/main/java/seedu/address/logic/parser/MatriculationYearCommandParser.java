package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATIONYEAR;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MatriculationYearCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatriculationYear;

/**
 * Parses input arguments and creates a new MatriculationYearCommand object
 */
public class MatriculationYearCommandParser implements Parser<MatriculationYearCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code MatriculationYearCommand}
     * and returns a {@code MatriculationYearCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MatriculationYearCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_MATRICULATIONYEAR);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatriculationYearCommand.MESSAGE_USAGE), ive);
        }

        String matriculationYear = argMultimap.getValue(PREFIX_MATRICULATIONYEAR).orElse("");

        return new MatriculationYearCommand(index, new MatriculationYear(matriculationYear));
    }
}
