package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be today or prior in YYYY-MM-DD format";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String DEFAULT_BIRTHDAY = "9999-01-01";

    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.value = birthday;
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        boolean formatIsCorrect = test.matches(VALIDATION_REGEX);
        if (!formatIsCorrect) {
            return false;
        }

        LocalDate dateEntered;
        try {
            dateEntered = LocalDate.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate todayDate = LocalDate.now();
            boolean dateIsValid = (dateEntered.isEqual(todayDate)) || (dateEntered.isBefore(todayDate)
                    || test.equals(DEFAULT_BIRTHDAY));
            if (!dateIsValid) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public boolean hasNoInfo() {
        return value.equals(DEFAULT_BIRTHDAY);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
