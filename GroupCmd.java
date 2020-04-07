import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    // TODO - javadoc once completed
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData is null");
        // TODO - check that the books array is not empty. if it is, print appropriate message

        List<BookEntry> books = data.getBookData();
        String header = "Grouped data by " + argument;
        System.out.println(header);

        if (argument.equals(TITLE_ARGUMENT)) groupTitle(books);
        else groupAuthor(books);

    }

    // TODO - javadoc once completed
    private void groupTitle(List<BookEntry> books){

        // Creating the Hashmap collection type used to group the books
        HashMap<Character, List<BookEntry>> map = new HashMap<>();

        for (BookEntry book : books){

            // Creating the key as the first character in title
            Character groupingToken = book.getTitle().toUpperCase().charAt(0);
            char token = groupingToken;

            // If the character already exists in the map
            if (map.containsKey(token)){
                List<BookEntry> list = map.get(token);
                list.add(book);

            // If the character does not exist in the map
            } else{
                List<BookEntry> list = new ArrayList<>();
                list.add(book);
                map.put(token, list);
            }

        }

        for (char token : map.keySet()){
            System.out.println("## " + token);
            List<BookEntry> valueList = map.get(token);
            for (BookEntry book : valueList){
                System.out.println("\t" + book.getTitle());
            }
        }




        // then create a temporary array list with all the books under a section
        // then print the elements in the array list

    }

    // TODO - javadoc once completed
    private void groupAuthor(List<BookEntry> books){

    }



    /**
     * Checks that the arguments inputted are valid for this command
     *
     * @param argumentInput argument input for this command
     * @return boolean value indicating whether the argument is valid or not
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: Argument is null");
        argument = argumentInput;
        return argument.equals(TITLE_ARGUMENT) || argument.equals(AUTHOR_ARGUMENT);
    }
}

// Goodbye, World!
