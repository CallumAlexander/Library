import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/** 
 * Class responsible for loading
 * book data from file.
 */
public class LibraryFileLoader {

    /**
     * Contains all lines read from a book data file using
     * the loadFileContent method.
     * 
     * This field can be null if loadFileContent was not called
     * for a valid Path yet.
     * 
     * NOTE: Individual line entries do not include line breaks at the 
     * end of each line.
     */
    private List<String> fileContent;

    /** Create a new loader. No file content has been loaded yet. */
    public LibraryFileLoader() { 
        fileContent = null;
    }

    /**
     * Load all lines from the specified book data file and
     * save them for later parsing with the parseFileContent method.
     * 
     * This method has to be called before the parseFileContent method
     * can be executed successfully.
     * 
     * @param fileName file path with book data
     * @return true if book data could be loaded successfully, false otherwise
     * @throws NullPointerException if the given file name is null
     */
    public boolean loadFileContent(Path fileName) {
        Objects.requireNonNull(fileName, "Given filename must not be null.");
        boolean success = false;

        try {
            fileContent = Files.readAllLines(fileName);
            success = true;
        } catch (IOException | SecurityException e) {
            System.err.println("ERROR: Reading file content failed: " + e);
        }

        return success;
    }

    /**
     * Has file content been loaded already?
     * @return true if file content has been loaded already.
     */
    public boolean contentLoaded() {
        return fileContent != null;
    }

    /**
     * Parse file content loaded previously with the loadFileContent method.
     * 
     * @return books parsed from the previously loaded book data or an empty list
     * if no book data has been loaded yet.
     * @throws UnsupportedOperationException Not implemented yet!
     */
    public List<BookEntry> parseFileContent() {

        ArrayList<BookEntry> books = new ArrayList<>();
        String handle = "";

        String[] dataArray;
        List<String> authorArrayList;

        if (fileContent == null){
            System.err.print("ERROR: No content loaded before parsing");
            return books;
        }
        for (int i = 1; i < fileContent.size(); i++){   //from index 1 to avoid the file header

            handle = fileContent.get(i);
            dataArray = handle.split(",");
            String authorString = dataArray[1];
            authorArrayList = parseAuthors(authorString);
            String[] authorArray = new String[authorArrayList.size()];
            authorArray = authorArrayList.toArray(authorArray);

            books.add(new BookEntry(dataArray[0],
                                    authorArray,
                                    Float.parseFloat(dataArray[2]),
                                    dataArray[3],
                                    Integer.parseInt(dataArray[4])));
        }

        return books;


    }

    public List<String> parseAuthors(String authorString){
        // Author string parse character
        char AUTHOR_PARSE_CHAR = '-';
        return Arrays.asList(authorString.split(Character.toString(AUTHOR_PARSE_CHAR)));
    }
}

