public class GroupCmd extends LibraryCommand {

    // ** DATA **

    // ** METHODS **

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param type          specific command type
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public GroupCmd(String argumentInput) { super(CommandType.GROUP, argumentInput); }


    @Override
    public void execute(LibraryData data) {

    }
}
