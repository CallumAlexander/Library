import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCmd extends LibraryCommand {

    // ** DATA **

    private String argument;


    // ** METHODS **

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param type - specific command type
     * @param argumentInput - argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public SearchCmd( String argumentInput) { super(CommandType.SEARCH, argumentInput);}

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData is null");
        List<BookEntry> books = data.getBookData();
        if (books.isEmpty()) throw new NullPointerException("ERROR: Library is empty");

        boolean found = false;
        for (BookEntry book : books) {
            if (book.getTitle().toUpperCase().contains(argument.toUpperCase())) {
                System.out.println(book.getTitle());
                found = true;
            }
        }
        if (!found) System.out.println("No hits found for search term: " + argument);
    }


    /**
     * Checks that the arguments inputted are valid for this command
     *
     * @param argumentInput argument input for this command
     * @return - boolean value indicating whether the argument is valid or not
     * @throws NullPointerException if argumentInput is null
     */
    @Override
    protected boolean parseArguments(String argumentInput){
        Objects.requireNonNull(argumentInput, "ERROR: Argument is null");
        argument = argumentInput.strip();
        if (argumentInput.isBlank()) return false;
        return !containsWhitespace(argument);
    }


    /**
     * Checks a string to see if it contains any whitespace
     *
     * @param argument - String containing the argument to check
     * @return - boolean value indicating whether the argument contains whitespace
     * NOTE - No need to check for illegal/null argument as this is checked in parseArguments
     */
    private boolean containsWhitespace(String argument){
        Objects.requireNonNull(argument, "ERROR: Argument is null");
        Pattern whitespace = Pattern.compile("\\s");
        Matcher matcher = whitespace.matcher(argument);
        return matcher.find();
    }
}
