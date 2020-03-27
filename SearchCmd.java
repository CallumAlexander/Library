import java.awt.print.Book;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCmd extends LibraryCommand {

    // TODO - JAVA doc for all methods
    // TODO - Implement IllegalArgumentExceptions for all methods

    // ** DATA **

    protected String argument;


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
        Objects.requireNonNull(data, "ERROR: data argument is null");
        List<BookEntry> books = data.getBookData();
        boolean found = false;
        for (BookEntry book : books) {
            if (book.getTitle().contains(argument)) {
                System.out.println(book.getTitle());
                found = true;
            }
        }
        if (!found) System.out.println("No hits found for search term: " + argument);
    }

    // TODO - java doc
    @Override
    public boolean parseArguments(String argumentInput){
        Objects.requireNonNull(argumentInput, "ERROR: Argument is null");
        argument = argumentInput.strip().toUpperCase();
        if (argumentInput.isBlank()) return false;
        return !containsWhitespace(argument);
    }

    // TODO - java doc
    public boolean containsWhitespace(String argument){
        Pattern whitespace = Pattern.compile("\\s");
        Matcher matcher = whitespace.matcher(argument);
        return matcher.find();
    }
}
