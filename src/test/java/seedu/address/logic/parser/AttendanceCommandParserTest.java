package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SECOND_PERSONS;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AttendanceCommand;

public class AttendanceCommandParserTest {

    private AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsAttendanceCommand() {
        LocalDate date = LocalDate.parse("2024-03-03");
        assertParseSuccess(parser, "1 2 d/2024-03-03", new AttendanceCommand(INDEX_FIRST_SECOND_PERSONS,
                date));
    }

    @Test
    public void parse_invalidIndexArg_throwsParseException() {
        assertParseFailure(parser, "-11 d/2024-03-03", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AttendanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDateArgs_throwsParseException() {
        assertParseFailure(parser, "1 d/03-03-2024", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AttendanceCommand.MESSAGE_USAGE));
    }
}
