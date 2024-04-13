package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.DateValidatorUtil.isValidDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.model.util.DateValidatorUtil;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be today or prior in YYYY-MM-DD format";
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
     *
     * @param test String to be tested.
     * @return True if the string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        LocalDate dateEntered;
        try {
            dateEntered = LocalDate.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean isToday = DateValidatorUtil.isToday(dateEntered);
            boolean isBeforeToday = DateValidatorUtil.isBeforeToday(dateEntered);
            boolean isDefaultBirthday = test.equals(DEFAULT_BIRTHDAY);
            return isValidDate(test) && (isToday || isBeforeToday || isDefaultBirthday);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean hasNoInfo() {
        return value.equals(DEFAULT_BIRTHDAY);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if a given object is the same as this Birthday object.
     *
     * @param other The other object to compare with.
     * @return True if the other object is the same Birthday object or has the same birthday value.
     */
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
