import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveCmd extends LibraryCommand {

    // ** DATA **

    // String instance field containing the specifier argument
    protected String specifer;

    // String instance field containing the value argument
    protected String val;

    // String instance field containing the argument for title
    protected static final String TITLE = "TITLE";

    // String instance field containing the argument for author
    protected static final String AUTHOR = "AUTHOR";

    // Integer instance field containing the number of arguments
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


    /**
     * Executes the remove command
     *
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if the library data is null.
     */
    @Override
    public void execute(LibraryData data) {

        Objects.requireNonNull(data, "ERROR: LibraryData is null");
        List<BookEntry> books = data.getBookData();
        if (books.isEmpty()) throw new NullPointerException("ERROR: Library is empty");

        if (specifer.equals(TITLE)) removeTitle(books);
        else removeAuthor(books);


    }

    /**
     * Removes the book associated with the title from the list of books
     *
     * @param books - List of books containing all the books in the library
     */
    private void removeTitle(List<BookEntry> books){

        boolean found = false;
        for (BookEntry book : books){

            if (book.getTitle().toUpperCase().contains(val.toUpperCase())){     //Convert both to upper case to eliminate case sensitivity
                books.remove(book);
                System.out.println(val + ": removed successfully.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println(val + ": not found.");

    }

    /**
     * Removes books associated with the author from the list of books
     *
     * @param books - List of BookEntry containing all the books in the library
     */
    private void removeAuthor(List<BookEntry> books){

        int count = 0;

        for (BookEntry book : books){

            for (int i = 0; i < book.getAuthors().length; i++){

                if (book.getAuthors()[i].toUpperCase().contains(val.toUpperCase())){
                    books.remove(book);
                    count ++;
                    break;
                }
            }
        }
        System.out.println(count + " books removed for author: " + val);
    }


    /**
     * Checks that the arguments inputted are valid for this command
     *
     * @param argumentInput argument input for this command
     * @return - boolean value indicating whether the arguments are valid or not
     */
    @Override
    protected boolean parseArguments(String argumentInput) {

        Objects.requireNonNull(argumentInput, "ERROR: argument is null");
        String[] tempArgsAsArray;

        // Checking if the whole string is blank
        if (argumentInput.isBlank()) return false;
        if (!containsWhitespace(argumentInput)) return false;

        // Checking if either of the arguments are blank
        tempArgsAsArray = argumentInput.split("\\s", NUMBER_OF_ARGUMENTS);
        if (tempArgsAsArray[0].isBlank() || tempArgsAsArray[1].isBlank()) return false;

        // Assigning the arguments to object instance fields
        specifer = tempArgsAsArray[0];
        val = tempArgsAsArray[1];

        return specifer.equals(AUTHOR) || specifer.equals(TITLE);
    }

    /**
     * Checks a string to see if it contains any whitespace
     *
     * @param argument - String containing the argument to check
     * @return - boolean value indicating whether the argument contains whitespace
     * NOTE - No need to check for illegal/null argument as this is checked in parseArguments
     */
    private boolean containsWhitespace(String argument){

        Pattern whitespace = Pattern.compile("\\s");
        Matcher matcher = whitespace.matcher(argument);
        return matcher.find();
    }


}
