import java.util.Objects;

public class GroupCmd extends LibraryCommand {

    // ** DATA **

    // A String instance fields containing the parsed argument
    private String argument;

    // A String instance field containing the title argument
    private static final String TITLE_ARGUMENT = "TITLE";

    // A String instance field containing the author argument
    private static final String AUTHOR_ARGUMENT = "AUTHOR";

    // ** METHODS **

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public GroupCmd(String argumentInput) { super(CommandType.GROUP, argumentInput); }


    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData is null");
        // TODO - check that the books array is not empty. if it is, print appropriate message

    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: Argument is null");
        argument = argumentInput;
        return argument.equals(TITLE_ARGUMENT) || argument.equals(AUTHOR_ARGUMENT);
    }
}
