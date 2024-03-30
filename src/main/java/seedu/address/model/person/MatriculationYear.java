package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Year;

/**
 * Represents a Person's matriculation year in the address book.
 * Guarantees: immutable; is always valid (year is a string of a year that is on or before the current year)
 */
public class MatriculationYear {
    public static final String MESSAGE_CONSTRAINTS =
            "Matriculation year should be this year or prior in YYYY format";
    public static final String VALIDATION_REGEX = "\\d{4}";
    public static final String DEFAULT_MATRICULATION_YEAR = "0000";
    public final String value;

    /**
     * Constructs a {@code MatriculationYear}.
     *
     * @param matriculationYear A valid matriculation year.
     */
    public MatriculationYear(String matriculationYear) {
        requireNonNull(matriculationYear);
        checkArgument(isValidMatriculationYear(matriculationYear), MESSAGE_CONSTRAINTS);
        this.value = matriculationYear;
    }

    /**
     * Returns true if a given string is a valid matriculation year.
     */
    public static boolean isValidMatriculationYear(String test) {
        boolean formatIsCorrect = test.matches(VALIDATION_REGEX);
        if (!formatIsCorrect) {
            return false;
        }

        Year thisYear = Year.now();
        Year yearEntered = Year.parse(test);
        boolean yearIsValid = (yearEntered.equals(thisYear)) || (yearEntered.isBefore(thisYear));
        if (!yearIsValid) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationYear // instanceof handles nulls
                && value.equals(((MatriculationYear) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
