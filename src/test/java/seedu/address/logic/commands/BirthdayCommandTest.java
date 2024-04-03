package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Birthday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;

public class BirthdayCommandTest {

    @Test
    public void equals() {
        Index sampleIndexOne = Index.fromOneBased(1);
        BirthdayCommand amyBirthdayCommand = new BirthdayCommand(sampleIndexOne, new Birthday(VALID_BIRTHDAY_AMY));
        BirthdayCommand bobBirthdayCommand = new BirthdayCommand(sampleIndexOne, new Birthday(VALID_BIRTHDAY_BOB));

        // same object -> returns true
        assertTrue(amyBirthdayCommand.equals(amyBirthdayCommand));

        // same values -> returns true
        BirthdayCommand amyBirthdayCommandCopy = new BirthdayCommand(sampleIndexOne, new Birthday(VALID_BIRTHDAY_AMY));
        assertTrue(amyBirthdayCommand.equals(amyBirthdayCommandCopy));

        // different types -> returns false
        assertFalse(amyBirthdayCommand.equals(1));

        // null -> returns false
        assertFalse(amyBirthdayCommand.equals(null));

        // different index number -> returns false
        Index sampleIndexTwo = Index.fromOneBased(2);
        BirthdayCommand amyBirthdayCommandDifferentIndexNum =
                new BirthdayCommand(sampleIndexTwo, new Birthday(VALID_BIRTHDAY_AMY));
        assertFalse(amyBirthdayCommand.equals(amyBirthdayCommandDifferentIndexNum));

        // different index base -> returns false
        Index sampleIndexOneDiffBase = Index.fromZeroBased(1);
        BirthdayCommand amyBirthdayCommandDifferentIndexBase =
                new BirthdayCommand(sampleIndexOneDiffBase, new Birthday(VALID_BIRTHDAY_AMY));
        assertFalse(amyBirthdayCommand.equals(amyBirthdayCommandDifferentIndexBase));

        // different birthday -> returns false
        assertFalse(amyBirthdayCommand.equals(bobBirthdayCommand));
    }
}
