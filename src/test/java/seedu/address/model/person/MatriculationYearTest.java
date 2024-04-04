package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class MatriculationYearTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatriculationYear(null));
    }

    @Test
    public void constructor_invalidBirthday_thorwsIllegalArgumentException() {
        String invalidMatriculationYear = "";
        assertThrows(IllegalArgumentException.class, () -> new MatriculationYear(invalidMatriculationYear));
    }

    @Test
    public void isValidMatriculationYear() {
        // null matriculation year
        assertThrows(NullPointerException.class, () -> MatriculationYear.isValidMatriculationYear(null));

        // invalid matriculation years
        assertFalse(MatriculationYear.isValidMatriculationYear("")); // empty birthday
        assertFalse(MatriculationYear.isValidMatriculationYear(" ")); // spaces only
        assertFalse(MatriculationYear.isValidMatriculationYear("-5")); // negative year not allowed
        assertFalse(MatriculationYear.isValidMatriculationYear("55555")); // should not be 5 digits

        // valid matriculation years
        assertTrue(MatriculationYear.isValidMatriculationYear("2000")); // correct format and before this year
        assertTrue(MatriculationYear.isValidMatriculationYear(Year.now().toString())); // valid year
    }

    @Test
    public void equals() {
        MatriculationYear matriculationYear = new MatriculationYear("2023");

        // same valies -> returns true
        assertTrue(matriculationYear.equals(new MatriculationYear("2023")));

        // same object -> returns true
        assertTrue(matriculationYear.equals(matriculationYear));

        // null -> returns false
        assertFalse(matriculationYear.equals(null));

        // different types -> returns false
        assertFalse(matriculationYear.equals("2024"));

        // different values -> returns false
        assertFalse(matriculationYear.equals(new MatriculationYear("2022")));
    }
}
