package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MatriculationYear;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MatriculationYearCommand.MESSAGE_ADD_MATRICULATION_YEAR_SUCCESS;
import static seedu.address.logic.commands.MatriculationYearCommand.MESSAGE_DELETE_MATRICULATION_YEAR_SUCCESS;
import static seedu.address.model.person.MatriculationYear.DEFAULT_MATRICULATION_YEAR;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class MatriculationYearCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Index sampleIndexOne = Index.fromOneBased(1);
        MatriculationYearCommand amyMatriculationYearCommand =
                new MatriculationYearCommand(sampleIndexOne, new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        MatriculationYearCommand bobMatriculationYearCommand =
                new MatriculationYearCommand(sampleIndexOne, new MatriculationYear(VALID_MATRICULATION_YEAR_BOB));

        // same object -> returns true
        assertTrue(amyMatriculationYearCommand.equals(amyMatriculationYearCommand));

        // same values -> returns true
        MatriculationYearCommand amyMatriculationYearCommandCopy =
                new MatriculationYearCommand(sampleIndexOne, new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        assertTrue(amyMatriculationYearCommand.equals(amyMatriculationYearCommandCopy));

        // different types -> returns false
        assertFalse(amyMatriculationYearCommand.equals(1));

        // null -> returns false
        assertFalse(amyMatriculationYearCommand.equals(null));

        // different index number -> returns false
        Index sampleIndexTwo = Index.fromOneBased(2);
        MatriculationYearCommand amyMatriculationYearCommandDifferentIndexNum =
                new MatriculationYearCommand(sampleIndexTwo, new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        assertFalse(amyMatriculationYearCommand.equals(amyMatriculationYearCommandDifferentIndexNum));

        // different index base -> returns false
        Index sampleIndexOneDiffBase = Index.fromZeroBased(1);
        MatriculationYearCommand amyMatriculationYearCommandDifferentIndexBase =
                new MatriculationYearCommand(sampleIndexOneDiffBase,
                        new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        assertFalse(amyMatriculationYearCommand.equals(amyMatriculationYearCommandDifferentIndexBase));

        // different birthday -> returns false
        assertFalse(amyMatriculationYearCommand.equals(bobMatriculationYearCommand));
    }

    /**
     * Create matriculation year command to add new (valid) matriculation year
     */
    @Test
    public void execute_addMatriculationYear_success() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java
        MatriculationYearCommand validAliceMatriculationYearCommand =
                new MatriculationYearCommand(aliceIndex, new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        String aliceSuccessMessage = String.format(MESSAGE_ADD_MATRICULATION_YEAR_SUCCESS, ALICE);
        assertCommandSuccess(validAliceMatriculationYearCommand, model, aliceSuccessMessage, model);
    }

    /**
     * Create matriculation year command to remove current matriculation year (replace with default)
     */
    @Test
    public void execute_deleteMatriculationYear_success() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java
        MatriculationYearCommand validAliceMatriculationYearCommand =
                new MatriculationYearCommand(aliceIndex, new MatriculationYear(DEFAULT_MATRICULATION_YEAR));
        String aliceSuccessMessage = String.format(MESSAGE_DELETE_MATRICULATION_YEAR_SUCCESS, ALICE);
        assertCommandSuccess(validAliceMatriculationYearCommand, model, aliceSuccessMessage, model);
    }

    /**
     * Create matriculation year command where index is larger than size of list (invalid)
     */
    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MatriculationYearCommand invalidMatriculationYearCommand =
                new MatriculationYearCommand(outOfBoundIndex, new MatriculationYear(VALID_MATRICULATION_YEAR_AMY));
        assertCommandFailure(invalidMatriculationYearCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
