import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {


    // ** DATA **

    // Path instance field that stores the argument
    protected Path argument;

    // String instance field that contains the required file extension
    protected static final String EXTENSION = ".csv";

    // Constant that stores the length of '.csv'
    protected static final int EXTENSION_LENGTH = EXTENSION.length();


    // ** METHODS **

    /**
     * Constructor for AddCmd class
     * @param argumentInput - String containing the command argument
     */
    public AddCmd(String argumentInput){
        super(CommandType.ADD, argumentInput);
    }


    /**
     * Method that executes the command
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: LibraryData is null");
        data.loadData(argument);
    }


    /**
     * Checks that the arguments inputted are valid for this command
     *
     * @param argumentInput argument input for this command
     * @return boolean value indicating whether the argument is valid
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: argument is null\n");

        if (argumentInput.length() < EXTENSION_LENGTH) {
            return false;
        }

        int len = argumentInput.length();
        String extension = argumentInput.substring(len-EXTENSION_LENGTH, len);
        boolean returnValue = extension.equals(EXTENSION);
        argument = Paths.get(argumentInput);
        return returnValue;
    }

}
