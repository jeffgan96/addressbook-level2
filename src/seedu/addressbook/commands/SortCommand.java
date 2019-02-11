package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Sorts the address book by alphabetical order (increasing).
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book by alphabetical order. ";

    public static final String MESSAGE_SUCCESS = "Sort successful.";
    
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedList = addressBook.sortListAlphabetically();
        return new CommandResult(MESSAGE_SUCCESS, sortedList);
    }

}