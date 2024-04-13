package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Represents a command that fFormat full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    /**
     * Executes the HelpCommand and display the help window.
     *
     * @param model The model which the command should operate on.
     * @return A command result with the success message of showing help window.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
