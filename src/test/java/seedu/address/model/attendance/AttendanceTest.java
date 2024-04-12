package seedu.address.model.attendance;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendanceTest {

    @Test
    public void equals() {
        LocalDate sampleDateOne = LocalDate.parse("2024-02-02");
        Attendance attendanceOne = new Attendance(sampleDateOne);
        LocalDate sampleDateTwo = LocalDate.parse("2024-05-05");
        Attendance attendanceTwo = new Attendance(sampleDateTwo);

        // same values -> returns true
        assertTrue(attendanceOne.equals(new Attendance(LocalDate.parse("2024-02-02"))));

        // same object -> returns true
        assertTrue(attendanceOne.equals(attendanceOne));

        // null -> returns false
        assertFalse(attendanceOne.equals(null));

        // different types -> returns false
        assertFalse(attendanceOne.equals("2023-02-02"));

        // different values -> returns false
        assertFalse(attendanceOne.equals(attendanceTwo));
    }
}
