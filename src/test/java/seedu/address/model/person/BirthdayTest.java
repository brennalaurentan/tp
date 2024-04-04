package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Birthday.DEFAULT_BIRTHDAY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_thorwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday("")); // empty birthday
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("0000-00-00")); // is not a valid date
        assertFalse(Birthday.isValidBirthday("0000-01-01")); // year is not valid
        assertFalse(Birthday.isValidBirthday("2000-00-01")); // month is not valid
        assertFalse(Birthday.isValidBirthday("2000-01-00")); // day is not valid
        assertFalse(Birthday.isValidBirthday("01-01-2000")); // wrong format (should be yyyy-mm-dd)
        assertFalse(Birthday.isValidBirthday("1 Jan 2000")); // wrong format (should be yyyy-mm-dd)
        assertFalse(Birthday.isValidBirthday("3000-01-01")); // date is after today

        // valid birthdays
        assertTrue(Birthday.isValidBirthday("2000-01-01")); // correct format and valid date (before today)
        assertTrue(Birthday.isValidBirthday("2024-02-29")); // valid leap year
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("2022-01-01");

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("2022-01-01")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals("2022-01-01"));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("2004-03-03")));
    }

    @Test
    public void testHashCode_Symmetric() {
        Birthday x = new Birthday(DEFAULT_BIRTHDAY);
        Birthday y = new Birthday(DEFAULT_BIRTHDAY);

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }
}
