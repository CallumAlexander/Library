import java.util.List;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {

    // ** DATA **

    // String array containing the two arguments
    protected String[] arguments = new String[2];

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
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if the library data is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: library data is null");
        List<BookEntry> books = data.getBookData();

        if (arguments[0].equals(TITLE)) removeTitle(books);
        else removeAuthor(books);


    }

    /**
     * Removes the book associated with the title from the list of books
     * @param books - List of books containing all the books in the library
     */
    private void removeTitle(List<BookEntry> books){
        // TODO - Optimize search by combining searchcmd and removecmd searches
        boolean found = false;
        for (BookEntry book : books){
            if (book.getTitle().toUpperCase().contains(arguments[1].toUpperCase())){    //Convert both to upper case to eliminate case sensitivity
                books.remove(book);
                System.out.println(arguments[1] + ": removed successfully.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println(arguments[1] + ": not found.");

    }

    /**
     * Removes books associated with the author from the list of books
     * @param books - List of BookEntry containing all the books in the library
     */
    private void removeAuthor(List<BookEntry> books){
        int count = 0;
        boolean found = false;

        for (BookEntry book : books){
            for (int i = 0; i < book.getAuthors().length; i++){
                if (book.getAuthors()[i].toUpperCase().contains(arguments[1].toUpperCase())){
                    found = true;
                    books.remove(book);
                    count ++;
                    break;
                }
            }
        }
        if (found) System.out.println(count + " books removed for author: " + arguments[1]);
        else System.out.println(arguments[1] + ": not found.");
    }


    /**
     * Checks that the arguments inputted are valid for this command
     * @param argumentInput argument input for this command
     * @return - boolean value indicating whether the arguments are valid or not
     */
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
