package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toStringMethod() {
        Config config = new Config();
        String expected = Config.class.getCanonicalName() + "{logLevel=" + config.getLogLevel()
                + ", userPrefsFilePath=" + config.getUserPrefsFilePath() + "}";
        assertEquals(expected, config.toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);

        // same object -> returns true
        assertTrue(defaultConfig.equals(defaultConfig));

        // different types -> returns false
        assertFalse(defaultConfig.equals(1));
    }

    @Test
    public void hashCode_symmetric() {
        Config x = new Config();
        Config y = new Config();

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

}
