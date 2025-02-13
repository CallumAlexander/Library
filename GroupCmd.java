import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

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



    /**
     * Executes the group command
     *
     * METHOD OUTLINE:
     *      1. Argument checking, and error handling
     *      2. Checks if books is empty, if it is, prints corresponding message
     *      3. Printing necessary header
     *      4. Building TreeMaps for necessary grouping
     *      5. Printing grouped books
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {

        Objects.requireNonNull(data, "ERROR: LibraryData is null");

        List<BookEntry> books = data.getBookData();
        if (books.isEmpty()) {
            System.out.println("The library has no book entries.");
            return;
        }

        String header = "Grouped data by " + argument;
        System.out.println(header);

        TreeMap<String, List<BookEntry>> map; // Note - TreeMap is used instead of a HashMap, since the keys of TreeMap are sorted

        // Builds the correct TreeMap for the corresponding argument
        if (argument.equals(TITLE_ARGUMENT)) {
            map = buildTreeMap(books, true);
        } else {
            map = buildTreeMap(books, false);
        }

        // Prints the grouped books
        for (String token : map.keySet()){
            System.out.println("## " + token);
            List<BookEntry> valueList = map.get(token); // Creates a separate list of all the values of a corresponding key
            for (BookEntry book : valueList){
                System.out.println("\t" + book.getTitle());
            }
        }

    }


    /**
     * Builds up a TreeMap for the books based on the corresponding group argument
     *
     * @param books - List of BookEntry that contains all the books
     * @param isTitle - Boolean indicating whether we are grouping via title
     * @return map - A tree map with a String as the key, and a list of BookEntry as its values
     */
    private TreeMap<String, List<BookEntry>> buildTreeMap(List<BookEntry> books, boolean isTitle){

        // Creating the TreeMap collection type used to group the books
        TreeMap<String, List<BookEntry>> map = new TreeMap<>();
        String token;

        for (BookEntry book : books){

            if (isTitle){
                // Creating the key as the first character in title
                Character groupingToken = book.getTitle().toUpperCase().charAt(0);
                token = String.valueOf(groupingToken);
                checkAndInsert(map, book, token);

            } else{
                String[] authors = book.getAuthors();
                // Performs the checkAndInsert for each author of every book
                for (String author : authors) {
                    token = author;
                    checkAndInsert(map, book, token);
                }
            }

        }

        return map;
    }


    /**
     * Maps and inserts a book to an existing key. If an existing key does not exist, the map creates one
     * and inserts the book
     *
     * @param map - TreeMap containing the map
     * @param book - BookEntry containing data about a certain book
     * @param token - String containing the key value to check
     */
    private void checkAndInsert(TreeMap<String, List<BookEntry>> map, BookEntry book, String token){
        // If the key already exists in the map
        if (map.containsKey(token)){
            List<BookEntry> list = map.get(token);
            list.add(book);

            // If the key does not exist in the map
        } else{
            List<BookEntry> list = new ArrayList<>();
            list.add(book);
            map.put(token, list);
        }
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