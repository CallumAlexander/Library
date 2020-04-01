import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {

    // TODO - Implement IllegalArgumentExceptions for all methods


    // ** DATA **

    // Path instance field that stores the argument
    protected Path argument;

    //Constant that stores the length of '.csv'
    protected static final int EXTENSION_LENGTH = 4;


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
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: data parameter is null");
        data.loadData(argument);
    }


    /**
     * Method that checks the inputted argument is valid
     * @param argumentInput argument input for this command
     * @return boolean value indicating whether the argument is valid
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: argument is null");

        if (argumentInput.length() < EXTENSION_LENGTH) {
            System.err.print("ERROR: Not a valid argument");
            return false;
        }

        int len = argumentInput.length();
        String extension = argumentInput.substring(len-EXTENSION_LENGTH, len);
        boolean returnValue = extension.equals(".csv");
        argument = Paths.get(argumentInput);
        return returnValue;
    }

}
