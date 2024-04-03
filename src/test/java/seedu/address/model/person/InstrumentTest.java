package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InstrumentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Instrument(null));
    }

    @Test
    public void constructor_invalidInstrument_throwsIllegalArgumentException() {
        String invalidInstrument = "!!!";
        assertThrows(IllegalArgumentException.class, () -> new Instrument(invalidInstrument));
    }

    @Test
    public void isValidInstrument() {

        // null instrument
        assertThrows(NullPointerException.class, () -> Instrument.isValidInstrument(null));

        // invalid instruments
        assertFalse(Instrument.isValidInstrument("")); // empty string
        assertFalse(Instrument.isValidInstrument(" ")); // spaces only
        assertFalse(Instrument.isValidInstrument("!")); // not alphanumeric
        assertFalse(Instrument.isValidInstrument("Tuba 2")); // contains space
        assertFalse(Instrument.isValidInstrument("Clari_net")); // contains symbol

        // valid instruments
        assertTrue(Instrument.isValidInstrument("Tuba")); // is alphanumeric
        assertTrue(Instrument.isValidInstrument("Clarinet1")); // is alphanumeric
    }

    @Test
    public void equals() {
        Instrument instrument = new Instrument("Clarinet");

        // same values -> returns true
        assertTrue(instrument.equals(new Instrument("Clarinet")));

        // same object -> returns true
        assertTrue(instrument.equals(instrument));

        // null -> returns false
        assertFalse(instrument.equals(null));

        // different types -> returns false
        assertFalse(instrument.equals("Clarinet"));

        // different valees -> returns false
        assertFalse(instrument.equals(new Instrument("Tuba")));
    }
}
