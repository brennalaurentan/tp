package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalIndexes;

public class AttendanceCommandTest {

    private static final String VALID_ATTENDANCE = "2024-02-02";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        // ensure that BandBook list has at least one person (first person index is valid)
        assertTrue(INDEX_FIRST_PERSON.getZeroBased() < model.getAddressBook().getPersonList().size());

        // create validAttendanceCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand validAttendanceCommand = new AttendanceCommand(setWithValidIndex, validAttendanceDate);

        // create expectedMessage
        Person personToEdit = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).addAttendances(VALID_ATTENDANCE).build();
        Name editedPersonName = editedPerson.getName();
        Set<Name> editedNames = new HashSet<>();
        editedNames.add(editedPersonName);
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_MARK_ATTENDANCE_SUCCESS, editedNames);

        // create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(validAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // create AttendanceCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand validAttendanceComand = new AttendanceCommand(setWithValidIndex, validAttendanceDate);

        // create expectedMessage
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).addAttendances(VALID_ATTENDANCE).build();
        Name editedPersonName = editedPerson.getName();
        Set<Name> editedNames = new HashSet<>();
        editedNames.add(editedPersonName);
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_MARK_ATTENDANCE_SUCCESS, editedNames);

        // create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(validAttendanceComand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAttendanceUnfilteredList_failure() {

        // create validAttendanceCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand validAttendanceCommand = new AttendanceCommand(setWithValidIndex, validAttendanceDate);

        // create modelCopy with first person having VALID_ATTENDANCE
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person personToEdit = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withAttendances(VALID_ATTENDANCE).build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // create expectedMessage
        Name personToEditName = personToEdit.getName();
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_DUPLICATE_ATTENDANCE, personToEditName);

        assertCommandFailure(validAttendanceCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_duplicateAttendanceFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // create validAttendanceCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand validAttendanceCommand = new AttendanceCommand(setWithValidIndex, validAttendanceDate);

        // create modelCopy with first person having VALID_ATTENDANCE
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person personToEdit = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withAttendances(VALID_ATTENDANCE).build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // create expectedMessage
        Name personToEditName = personToEdit.getName();
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_DUPLICATE_ATTENDANCE, personToEditName);

        assertCommandFailure(validAttendanceCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> setWithInvalidIndex = new HashSet<>();
        setWithInvalidIndex.add(outOfBoundIndex);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand invalidAttendanceCommand = new AttendanceCommand(setWithInvalidIndex, validAttendanceDate);
        assertCommandFailure(invalidAttendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        Set<Index> setWithInvalidIndex = new HashSet<>();
        setWithInvalidIndex.add(outOfBoundIndex);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE);
        AttendanceCommand invalidAttendanceCommand = new AttendanceCommand(setWithInvalidIndex, validAttendanceDate);
        assertCommandFailure(invalidAttendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Index> indexSetWithFirstPerson = TypicalIndexes.INDEX_FIRST_PERSON_SET;
        Set<Index> indexSetWithSecondPerson = TypicalIndexes.INDEX_SECOND_PERSON_SET;
        LocalDate validAttendanceDate = LocalDate.parse("2024-02-02");
        AttendanceCommand addAttendanceToFirstIndexCommand =
                new AttendanceCommand(indexSetWithFirstPerson, validAttendanceDate);
        AttendanceCommand addAttendanceToSecondIndexCommand =
                new AttendanceCommand(indexSetWithSecondPerson, validAttendanceDate);

        // same object -> returns true
        assertTrue(addAttendanceToFirstIndexCommand.equals(addAttendanceToFirstIndexCommand));

        // same values -> returns true
        AttendanceCommand addAttendanceToFirstIndexCommandCopy =
                new AttendanceCommand(indexSetWithFirstPerson, validAttendanceDate);
        assertTrue(addAttendanceToFirstIndexCommand.equals(addAttendanceToFirstIndexCommandCopy));

        // different types -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(1));

        // null -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(null));

        // different index -> returns false
        assertFalse(addAttendanceToFirstIndexCommand.equals(addAttendanceToSecondIndexCommand));
    }
}
