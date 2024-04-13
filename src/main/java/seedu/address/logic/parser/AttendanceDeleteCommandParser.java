package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AttendanceDeleteCommand object
 */
public class AttendanceDeleteCommandParser implements Parser<AttendanceDeleteCommand> {

    /**
     * Parses the given string of arguments in the context of the AttendanceDeleteCommand
     * and returns a AttendanceDeleteCommand object for execution.
     *
     * @param args String of arguments to be parsed.
     * @return AttendanceDeleteCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AttendanceDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ATTENDANCE_DATE);

        Set<Index> indexes;
        try {
            indexes = ParserUtil.parseIndexes(List.of(argMultimap.getPreamble().split(" ")));
            LocalDate date = LocalDate.parse(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).orElse(""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return new AttendanceDeleteCommand(indexes, date);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceDeleteCommand.MESSAGE_USAGE), ive);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceDeleteCommand.MESSAGE_USAGE), e);
        }
    }

}
