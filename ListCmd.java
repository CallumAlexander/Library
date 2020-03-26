import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {

    // TODO - JAVA DOC for all methods

    protected String argument;

    protected static final String LONG_ARGUMENT = "long";

    protected static final String SHORT_ARGUMENT = "short";

    /**
     * Create the specified command and initialise it with
     * the given command arguments
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public ListCmd(String argumentInput) { super(CommandType.LIST, argumentInput); }


    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData instance is null");
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

    @Override
    protected boolean parseArguments(String argumentInput){
        // We do not require a null check for this method because it can be null
        // Assuming that the arguments are case sensitive

        if (argumentInput == null || argumentInput.equals("")) argument = SHORT_ARGUMENT;
        else argument = argumentInput;
        return argument.equals(LONG_ARGUMENT) || argument.equals(SHORT_ARGUMENT);

    }


}
