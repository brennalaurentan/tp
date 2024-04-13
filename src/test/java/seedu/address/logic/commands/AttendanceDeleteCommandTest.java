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

public class AttendanceDeleteCommandTest {

    private static final String VALID_ATTENDANCE_1 = "2024-02-02";
    private static final String VALID_ATTENDANCE_2 = "2024-03-03";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        // ensure that BandBook list has at least one person (first person index is valid)
        assertTrue(INDEX_FIRST_PERSON.getZeroBased() < model.getAddressBook().getPersonList().size());

        // set attendances of person at first index
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPerson = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedFirstPerson = new PersonBuilder(firstPerson).withAttendances(VALID_ATTENDANCE_1).build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedFirstPerson);

        // create validAttendanceDeleteCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDateToDelete = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand validAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithValidIndex, validAttendanceDateToDelete);

        // create expectedMessage
        Name editedPersonName = editedFirstPerson.getName();
        Set<Name> editedNames = new HashSet<>();
        editedNames.add(editedPersonName);
        String expectedMessage = String.format(AttendanceDeleteCommand.MESSAGE_UNMARK_ATTENDANCE_SUCCESS, editedNames);

        // create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPersonWithNoAttendances = new PersonBuilder(firstPerson).withAttendances().build();
        expectedModel.setPerson(model.getFilteredPersonList().get(0), firstPersonWithNoAttendances);

        assertCommandSuccess(validAttendanceDeleteCommand, modelCopy, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        // set attendances of person at first index
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPerson = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedFirstPerson = new PersonBuilder(firstPerson).withAttendances(VALID_ATTENDANCE_1).build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedFirstPerson);

        showPersonAtIndex(modelCopy, INDEX_FIRST_PERSON);

        // create validAttendanceDeleteCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDateToDelete = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand validAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithValidIndex, validAttendanceDateToDelete);

        // create expectedMessage
        Name editedPersonName = editedFirstPerson.getName();
        Set<Name> editedNames = new HashSet<>();
        editedNames.add(editedPersonName);
        String expectedMessage = String.format(AttendanceDeleteCommand.MESSAGE_UNMARK_ATTENDANCE_SUCCESS, editedNames);

        // create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPersonWithNoAttendances = new PersonBuilder(firstPerson).withAttendances().build();
        expectedModel.setPerson(model.getFilteredPersonList().get(0), firstPersonWithNoAttendances);

        assertCommandSuccess(validAttendanceDeleteCommand, modelCopy, expectedMessage, expectedModel);
    }

    @Test
    public void execute_attendanceNotMarkedUnfilteredList_failure() {
        // ensure that BandBook list has at least one person (first person index is valid)
        assertTrue(INDEX_FIRST_PERSON.getZeroBased() < model.getAddressBook().getPersonList().size());

        // set attendances of person at first index
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPerson = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedFirstPerson = new PersonBuilder(firstPerson).withAttendances().build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedFirstPerson);

        // create validAttendanceDeleteCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDateToDelete = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand invalidAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithValidIndex, validAttendanceDateToDelete);

        // create expectedMessage
        Name personToEditName = editedFirstPerson.getName();
        String expectedMessage = String.format(AttendanceDeleteCommand.MESSAGE_MISSING_ATTENDANCE, personToEditName);

        assertCommandFailure(invalidAttendanceDeleteCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_attendanceNotMarkedFilteredList_failure() {
        // set attendances of person at first index
        Model modelCopy = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstPerson = modelCopy.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedFirstPerson = new PersonBuilder(firstPerson).withAttendances().build();
        modelCopy.setPerson(model.getFilteredPersonList().get(0), editedFirstPerson);

        showPersonAtIndex(modelCopy, INDEX_FIRST_PERSON);

        // create validAttendanceDeleteCommand
        Set<Index> setWithValidIndex = new HashSet<>();
        setWithValidIndex.add(INDEX_FIRST_PERSON);
        LocalDate validAttendanceDateToDelete = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand invalidAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithValidIndex, validAttendanceDateToDelete);

        // create expectedMessage
        Name personToEditName = editedFirstPerson.getName();
        String expectedMessage = String.format(AttendanceDeleteCommand.MESSAGE_MISSING_ATTENDANCE, personToEditName);

        assertCommandFailure(invalidAttendanceDeleteCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> setWithInvalidIndex = new HashSet<>();
        setWithInvalidIndex.add(outOfBoundIndex);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand invalidAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithInvalidIndex, validAttendanceDate);
        assertCommandFailure(invalidAttendanceDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        Set<Index> setWithInvalidIndex = new HashSet<>();
        setWithInvalidIndex.add(outOfBoundIndex);
        LocalDate validAttendanceDate = LocalDate.parse(VALID_ATTENDANCE_1);
        AttendanceDeleteCommand invalidAttendanceDeleteCommand =
                new AttendanceDeleteCommand(setWithInvalidIndex, validAttendanceDate);
        assertCommandFailure(invalidAttendanceDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Index> indexSetWithFirstPerson = TypicalIndexes.INDEX_FIRST_PERSON_SET;
        Set<Index> indexSetWithSecondPerson = TypicalIndexes.INDEX_SECOND_PERSON_SET;
        LocalDate validAttendanceDate = LocalDate.parse("2024-02-02");

        AttendanceDeleteCommand deleteAttendanceFromFirstIndexCommand =
                new AttendanceDeleteCommand(indexSetWithFirstPerson, validAttendanceDate);
        AttendanceDeleteCommand deleteAttendanceFromSecondIndexCommand =
                new AttendanceDeleteCommand(indexSetWithSecondPerson, validAttendanceDate);

        // same object -> returns true
        assertTrue(deleteAttendanceFromFirstIndexCommand.equals(deleteAttendanceFromFirstIndexCommand));

        // same values -> returns true
        AttendanceDeleteCommand deleteAttendanceFromFirstIndexCommandCopy =
                new AttendanceDeleteCommand(indexSetWithFirstPerson, validAttendanceDate);
        assertTrue(deleteAttendanceFromFirstIndexCommand.equals(deleteAttendanceFromFirstIndexCommandCopy));

        // different types -> returns false
        assertFalse(deleteAttendanceFromFirstIndexCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAttendanceFromFirstIndexCommand.equals(null));

        // different index -> returns false
        assertFalse(deleteAttendanceFromFirstIndexCommand.equals(deleteAttendanceFromSecondIndexCommand));
    }
}
