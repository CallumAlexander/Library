import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveCmd extends LibraryCommand {

    // ** DATA **

    // String array containing the two arguments
    protected String[] arguments = new String[2];

    // String field instance containing the argument for title
    protected static final String TITLE = "TITLE";

    // String field instance containing the argument for author
    protected static final String AUTHOR = "AUTHOR";

    protected static final int NUMBER_OF_ARGUMENTS = 2;


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

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: argument is null");
        String tempArgs = argumentInput.strip();
        if (tempArgs.isBlank()) return false;

        arguments = tempArgs.split("\\s", NUMBER_OF_ARGUMENTS);
        if (!arguments[0].equals(AUTHOR) && !arguments[0].equals(TITLE)) return false;
        return true;

    }


}
