package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    private static final String SAMPLE_STRING_1 = "apple";
    private static final String SAMPLE_STRING_2 = "banana";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals() {
        Tag tagOne = new Tag(SAMPLE_STRING_1);
        Tag tagTwo = new Tag(SAMPLE_STRING_2);

        // same object -> returns true
        assertTrue(tagOne.equals(tagOne));

        // same values -> returns true
        Tag tagOneCopy = new Tag(SAMPLE_STRING_1);
        assertTrue(tagOne.equals(tagOneCopy));

        // different types -> returns false
        assertFalse(tagOne.equals(1));

        // null -> returns false
        assertFalse(tagOne.equals(null));

        // different tag -> returns false
        assertFalse(tagOne.equals(tagTwo));
    }

}
