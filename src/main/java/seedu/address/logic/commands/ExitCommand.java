package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Represents a command that terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    /**
     * Executes the command that terminates the program.
     *
     * @param model The model which the command should operate on.
     * @return A command result that the program has terminated successfully.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
