package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's assigned instrument in the address book.
 * Guarantees: immutable; is always valid
 */
public class Instrument {

    public static final String MESSAGE_CONSTRAINTS = "Instrument name should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String DEFAULT_INSTRUMENT = "None";

    public final String value;

    /**
     * Constructs a {@code Instrument}.
     *
     * @param instrument A valid instrument name.
     */
    public Instrument(String instrument) {
        requireNonNull(instrument);
        checkArgument(isValidInstrument(instrument), MESSAGE_CONSTRAINTS);
        this.value = instrument;
    }

    /**
     * Checks if a given string is a valid instrument.
     *
     * @param test The string to test.
     * @return True if the string is a valid instrument.
     */
    public static boolean isValidInstrument(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if a given object is the same as this Instrument object.
     *
     * @param other The other object to compare with.
     * @return True if the other object is the same Instrument object or has the same instrument value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Instrument)) {
            return false;
        }

        Instrument otherInstrument = (Instrument) other;
        return value.equals(otherInstrument.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean hasNoInfo() {
        return value.equals(DEFAULT_INSTRUMENT);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }
}
