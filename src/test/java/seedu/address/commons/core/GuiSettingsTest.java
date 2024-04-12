package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Birthday.DEFAULT_BIRTHDAY;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Birthday;

public class GuiSettingsTest {
    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings();

        double newWindowWidth = Math.random();
        double newWindowHeight = Math.random();
        int newXPosition = (int) (Math.random() * 10);
        int newYPosition = (int) (Math.random() * 10);
        GuiSettings newGuiSettings =
                new GuiSettings(newWindowWidth, newWindowHeight, newXPosition, newYPosition);

        // same values -> returns true
        assertTrue(guiSettings.equals(new GuiSettings()));

        // same object -> returns true
        assertTrue(guiSettings.equals(guiSettings));

        // null -> returns false
        assertFalse(guiSettings.equals(null));

        // different types -> returns false
        assertFalse(guiSettings.equals(1));

        // different values -> returns false
        assertFalse(guiSettings.equals(newGuiSettings));
    }

    @Test
    public void hashCode_symmetric() {
        GuiSettings x = new GuiSettings();
        GuiSettings y = new GuiSettings();

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }
}
