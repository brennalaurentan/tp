package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_ADD_BIRTHDAY_SUCCESS;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_DELETE_BIRTHDAY_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.Birthday.DEFAULT_BIRTHDAY;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Birthday;

public class BirthdayCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

    /**
     * Create birthday command with valid parameters
     */
    @Test
    public void execute_addBirthday_success() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java
        BirthdayCommand validAliceBirthdayCommand = new BirthdayCommand(aliceIndex, new Birthday(VALID_BIRTHDAY_AMY));
        String aliceSuccessMessage = String.format(MESSAGE_ADD_BIRTHDAY_SUCCESS, ALICE);
        assertCommandSuccess(validAliceBirthdayCommand, model, aliceSuccessMessage, model);
    }

    /**
     * Create birthday command to remove current birthday (replace with default)
     */
    @Test
    public void execute_deleteMatriculationYear_success() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java
        BirthdayCommand validAliceBirthdayCommand =
                new BirthdayCommand(aliceIndex, new Birthday(DEFAULT_BIRTHDAY));
        String aliceSuccessMessage = String.format(MESSAGE_DELETE_BIRTHDAY_SUCCESS, ALICE);
        assertCommandSuccess(validAliceBirthdayCommand, model, aliceSuccessMessage, model);
    }

    /**
     * Create birthday command where index is larger than size of list (invalid)
     */
    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BirthdayCommand invalidBirthdayCommand = new BirthdayCommand(outOfBoundIndex, new Birthday(VALID_BIRTHDAY_AMY));
        assertCommandFailure(invalidBirthdayCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
