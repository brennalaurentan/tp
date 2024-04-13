package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents an attendance entry in the address book.
 * Guarantees: immutable;
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance entry should follow the format YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final LocalDate attendanceDate;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendanceDate A valid attendance date.
     */
    public Attendance(LocalDate attendanceDate) {
        requireNonNull(attendanceDate);
        this.attendanceDate = attendanceDate;
    }

    /**
     * Checks if a given object is the same as this Attendance object.
     *
     * @param other The other object to compare with.
     * @return True if the other object is an instance of Attendance and has the same attendance date.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return attendanceDate.equals(otherAttendance.attendanceDate);
    }

    /**
     * Checks if a given string is a valid attendance date.
     *
     * @param test The string to test.
     * @return True if the string is a valid attendance date.
     */
    public static boolean isValidAttendanceDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return attendanceDate.hashCode();
    }

    /**
     * Returns a string representation of the Attendance object.
     *
     * @return A string representation of the Attendance object.
     */
    public String toString() {
        return '[' + attendanceDate.toString() + ']';
    }

}
