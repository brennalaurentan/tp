package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_YEAR;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Person;

/**
 * Represents a command that deletes a person identified using its displayed index from the address book. It is also
 * able to delete multiple persons identified by their matriculation year.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number or their matriculation year used in the "
            + "displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) or "
            + PREFIX_MATRICULATION_YEAR + "MATRICULATION_YEAR \n"
            + "Example: " + COMMAND_WORD + " 1 \n"
            + "Example: " + COMMAND_WORD + " my/2003 \n";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSONS_SUCCESS = "Deleted Persons: %1$s";

    private final Index targetIndex;
    private final MatriculationYear matriculationYear;

    /**
     * Constructs a DeleteCommand to delete the person at the specified index.
     *
     * @param targetIndex The index of the person in the filtered person list to delete.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.matriculationYear = new MatriculationYear(MatriculationYear.DEFAULT_MATRICULATION_YEAR);
    }

    /**
     * Constructs a DeleteCommand to delete the persons with the specified matriculation year.
     *
     * @param year The matriculation year used to identify the persons to be deleted in the filtered person list.
     */
    public DeleteCommand(MatriculationYear year) {
        this.targetIndex = null;
        this.matriculationYear = year;
    }

    /**
     * Executes the DeleteCommand to delete the person at the specified index or the persons with the specified
     * matriculation year.
     *
     * @param model The model which the command should operate on.
     * @return A command result that indicates that the person has been deleted successfully.
     * @throws CommandException If the index is invalid or the matriculation year is not provided.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!this.matriculationYear.equals(new MatriculationYear(MatriculationYear.DEFAULT_MATRICULATION_YEAR))) {
            model.updateFilteredPersonList(person -> person.getMatriculationYear().equals(this.matriculationYear));
            List<Person> toDelete = new ArrayList<>(model.getFilteredPersonList());
            StringBuilder deletedPersonsList = new StringBuilder();
            toDelete.forEach(personToDelete -> {
                model.deletePerson(personToDelete);
                deletedPersonsList.append("\n");
                deletedPersonsList.append(Messages.format(personToDelete));
            });

            model.updateFilteredPersonList(person -> true);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSONS_SUCCESS, deletedPersonsList));
        } else {
            List<Person> lastShownList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }
    }

    /**
     * Checks if the DeleteCommand is equal to another object.
     *
     * @param other The other object to compare with.
     * @return True if both DeleteCommands have the same index.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    /**
     * Returns the string representation of the DeleteCommand.
     *
     * @return The string representation of the DeleteCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
