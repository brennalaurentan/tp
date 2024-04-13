package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_YEAR;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatriculationYear;

/**
 * Represents a parser that parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @param args String of arguments to be parsed.
     * @return DeleteCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MATRICULATION_YEAR);
            MatriculationYear matriculationYear =
                    ParserUtil.parseMatriculationYear(argMultimap
                            .getOptionalMatriculationYear(PREFIX_MATRICULATION_YEAR).get());

            if (!matriculationYear.equals(new MatriculationYear(MatriculationYear.DEFAULT_MATRICULATION_YEAR))) {
                if (!argMultimap.getPreamble().isEmpty()) {
                    throw new ParseException("User entered both the index and matriculation year!");
                }
                return new DeleteCommand(matriculationYear);
            } else {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
