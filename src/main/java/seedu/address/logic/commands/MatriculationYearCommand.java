package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Person;

/**
 * Adds the matriculation year to a new contact in the address book, or
 * Changes the matriculation year of an existing person in the address book
 */
public class MatriculationYearCommand extends Command {

    public static final String COMMAND_WORD = "my";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Matriculation Year: %2$d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the matriculation year of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing matriculation year will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "my/[MATRICULATION YEAR in YYYY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "my/2000";
    public static final String MESSAGE_ADD_MATRICULATIONYEAR_SUCCESS = "Added matriculation year to Person: %1$s";
    public static final String MESSAGE_DELETE_MATRICULATIONYEAR_SUCCESS =
            "Removed matriculation year from Person: %1$s";

    private final Index index;
    private final MatriculationYear matriculationYear;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param matriculationYear of the person to be updated to
     */
    public MatriculationYearCommand(Index index, MatriculationYear matriculationYear) {
        requireAllNonNull(index, matriculationYear);

        this.index = index;
        this.matriculationYear = matriculationYear;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getBirthday(), personToEdit.getMatriculationYear(),
                personToEdit.getTags(), personToEdit.getAttendances());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the matriculation year is added to or removed from the person
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !matriculationYear.value.isEmpty()
                ? MESSAGE_ADD_MATRICULATIONYEAR_SUCCESS
                : MESSAGE_DELETE_MATRICULATIONYEAR_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatriculationYearCommand)) {
            return false;
        }

        MatriculationYearCommand e = (MatriculationYearCommand) other;
        return index.equals(e.index)
                && matriculationYear.equals(e.matriculationYear);
    }

}
