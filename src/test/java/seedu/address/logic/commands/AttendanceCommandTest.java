package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.testutil.TypicalIndexes;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendanceCommandTest {

    @Test
    public void execute_attendanceAcceptedByModel_attendanceAddSuccessful() {

    }

    @Test
    public void equals() {
        //Set<Attendance> aliceAttendanceSet = new HashSet<>(alice.getAttendances());
        //Set<Attendance> bobAttendanceSet = new HashSet<>(bob.getAttendances());
        Set<Index> indexSetWithFirstPerson = TypicalIndexes.INDEX_FIRST_PERSON_SET;
        Set<Index> indexSetWithSecondPerson = TypicalIndexes.INDEX_SECOND_PERSON_SET;
        LocalDate validAttendanceDate = LocalDate.parse("2024-02-02");
        AttendanceCommand addAttendanceToFirstIndexCommand =
                new AttendanceCommand(indexSetWithFirstPerson, validAttendanceDate);
        AttendanceCommand addAttendanceToSecondIndexCommand =
                new AttendanceCommand(indexSetWithSecondPerson, validAttendanceDate);

        // same object -> returns true
        assertTrue(addAttendanceToFirstIndexCommand.equals(addAttendanceToFirstIndexCommand));

        // same values -> returns true
        AttendanceCommand addAttendanceToFirstIndexCommandCopy =
                new AttendanceCommand(indexSetWithFirstPerson, validAttendanceDate);
        assertTrue(addAttendanceToFirstIndexCommand.equals(addAttendanceToFirstIndexCommandCopy));

        // different types -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(1));

        // null -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(null));

        // different index -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(addAttendanceToSecondIndexCommand));
    }
}
