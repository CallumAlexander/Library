public class RemoveCmd extends LibraryCommand {

    // ** DATA **


    // ** METHODS **

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public RemoveCmd(String argumentInput) { super(CommandType.REMOVE, argumentInput);}

    @Override
    public void execute(LibraryData data) {

    }



}
