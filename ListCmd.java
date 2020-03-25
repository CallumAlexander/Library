public class ListCmd extends LibraryCommand {


    /**
     * Create the specified command and initialise it with
     * the given command arguments
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {

    }

    // TODO - 3rd bullet point onwards in task 3


}
