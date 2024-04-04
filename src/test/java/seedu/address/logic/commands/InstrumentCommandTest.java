package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InstrumentCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class InstrumentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index sampleIndexOne = Index.fromOneBased(1);
        Index sampleIndexTwo = Index.fromOneBased(2);
        Index sampleIndexThree = Index.fromOneBased(3);
        Set<Index> indexSetWithOne = new HashSet<>();
        indexSetWithOne.add(sampleIndexOne);
        Set<Index> indexSetWithTwo = new HashSet<>();
        indexSetWithTwo.add(sampleIndexTwo);
        InstrumentCommand amyInstrumentCommand = 
                new InstrumentCommand(indexSetWithOne, new Instrument(VALID_INSTRUMENT_AMY));
        InstrumentCommand bobInstrumentCommand =
                new InstrumentCommand(indexSetWithOne, new Instrument(VALID_INSTRUMENT_BOB));
        
        // same object -> returns true
        assertTrue(amyInstrumentCommand.equals(amyInstrumentCommand));

        // same values -> returns true
        InstrumentCommand amyInstrumentCommandCopy = 
                new InstrumentCommand(indexSetWithOne, new Instrument(VALID_INSTRUMENT_AMY));
        assertTrue(amyInstrumentCommand.equals(amyInstrumentCommandCopy));

        // different types -> returns false
        assertFalse(amyInstrumentCommand.equals(1));

        // null -> returns false
        assertFalse(amyInstrumentCommand.equals(null));

        // different index number -> returns false
        InstrumentCommand amyInstrumentCommandDifferentIndexNum =
                new InstrumentCommand(indexSetWithTwo, new Instrument(VALID_INSTRUMENT_AMY));
        assertFalse(amyInstrumentCommand.equals(amyInstrumentCommandDifferentIndexNum));

        // different index base -> returns false
        Index sampleIndexOneDiffBase = Index.fromZeroBased(1);
        Set<Index> indexSetWithOne_zeroBase = new HashSet<>();
        indexSetWithOne_zeroBase.add(sampleIndexOneDiffBase);
        InstrumentCommand amyInstrumentCommandDifferentIndexBase =
                new InstrumentCommand(indexSetWithOne_zeroBase, new Instrument(VALID_INSTRUMENT_AMY));
        assertFalse(amyInstrumentCommand.equals(amyInstrumentCommandDifferentIndexBase));

        // different instrument -> returns false
        assertFalse(amyInstrumentCommand.equals(bobInstrumentCommand));
    }

    /**
     * Create instrument command with valid parameters
     */
    @Test
    public void execute_success() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java
        Name aliceName = new Name("Alice Pauline");
        Set<Index> aliceIndexSet = new HashSet<>();
        aliceIndexSet.add(aliceIndex);
        Set<Name> aliceNameSet = new HashSet<>();
        aliceNameSet.add(aliceName);
        InstrumentCommand validAliceInstrumentCommand =
                new InstrumentCommand(aliceIndexSet, new Instrument(VALID_INSTRUMENT_BOB));
        String aliceSuccessMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, aliceNameSet);
        assertCommandSuccess(validAliceInstrumentCommand, model, aliceSuccessMessage, model);
    }

    /**
     * Create instrument command where index is larger than size of list (invalid)
     */
    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> invalidIndexSet = new HashSet<>();
        invalidIndexSet.add(outOfBoundIndex);
        InstrumentCommand invalidInstrumentCommand =
                new InstrumentCommand(invalidIndexSet, new Instrument(VALID_INSTRUMENT_AMY));
        assertCommandFailure(invalidInstrumentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Create instrument command which assigns an instrument which is already assigned (invalid because duplicate)
     */
    @Test
    public void execute_duplicateInstrument_throwsCommandException() {
        Index aliceIndex = Index.fromOneBased(1); // alice's index in TypicalPersons.java

        Person alice = model.getFilteredPersonList().get(0);
        Name aliceName = alice.getName();
        Instrument aliceInstrument = alice.getInstrument();
        String aliceInstrumentStr = aliceInstrument.toString();
        Instrument aliceInstrumentCopy = new Instrument(aliceInstrumentStr);

        Set<Index> aliceIndexSet = new HashSet<>();
        aliceIndexSet.add(aliceIndex);

        InstrumentCommand aliceInstrumentCommandSameInstrumentCopyObject =
                new InstrumentCommand(aliceIndexSet, aliceInstrumentCopy);

        InstrumentCommand aliceInstrumentCommandSameInstrumentSameObject =
                new InstrumentCommand(aliceIndexSet, aliceInstrument);

        String aliceFailureMessage = String.format(Messages.MESSAGE_DUPLICATE_INSTRUMENT, aliceName);

        // new instrument object created using same string -> fails as it counts as duplicate
        assertCommandFailure(aliceInstrumentCommandSameInstrumentCopyObject, model,
                aliceFailureMessage);

        // same existing instrument object added to user -> fails as it counts as duplicate
        assertCommandFailure(aliceInstrumentCommandSameInstrumentSameObject, model,
                aliceFailureMessage);
    }
}
