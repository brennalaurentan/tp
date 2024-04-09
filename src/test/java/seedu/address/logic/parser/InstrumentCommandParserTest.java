package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SECOND_PERSONS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InstrumentCommand;
import seedu.address.model.person.Instrument;


public class InstrumentCommandParserTest {

    private InstrumentCommandParser parser = new InstrumentCommandParser();

    @Test
    public void parse_validArgs_returnsInstrumentCommand() {
        Instrument instrument = new Instrument("flute");
        assertParseSuccess(parser, "1 2 i/flute", new InstrumentCommand(INDEX_FIRST_SECOND_PERSONS,
                instrument));
    }

    @Test
    public void parse_invalidIndexArg_throwsParseException() {
        assertParseFailure(parser, "-11 i/flute", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                InstrumentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInstrumentArg_throwsParseException() {
        assertParseFailure(parser, "1 i/!!!", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                InstrumentCommand.MESSAGE_USAGE));
    }
}
