import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {


    // TODO - Implement IllegalArgumentExceptions for all methods

    // ** DATA **

    // String instance field that stores the argument
    protected String argument;

    // String instance field that stores the long argument keyword
    protected static final String LONG_ARGUMENT = "long";

    // String instance field that stores the short argument keyword
    protected static final String SHORT_ARGUMENT = "short";

    /**
     * Create the specified command and initialise it with
     * the given command arguments
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public ListCmd(String argumentInput) { super(CommandType.LIST, argumentInput); }


    /**
     * Method that executes the command
     *
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData instance is null");
        // TODO - check that the books array is not empty. if it is, print appropriate message
        List<BookEntry> books = data.getBookData();

        // Prints header
        if (books.isEmpty()) System.out.println("The library has no book entries");
        else System.out.println(books.size() + " books in library:");

        // Prints books
        if (argument.equals(SHORT_ARGUMENT)){
            for (BookEntry book : books) System.out.println(book.getTitle());
        } else {
            for (BookEntry book : books) System.out.println(book.toString() + "\n");
        }

    }

    /**
     * Checks that the arguments inputted are valid for this command
     *
     * @param argumentInput argument input for this command
     * @return boolean value indicating whether the argument is valid or not
     */
    @Override
    protected boolean parseArguments(String argumentInput){
        if (argumentInput == null || argumentInput.equals("")) argument = SHORT_ARGUMENT;
        else argument = argumentInput;
        return argument.equals(LONG_ARGUMENT) || argument.equals(SHORT_ARGUMENT);
    }


}
