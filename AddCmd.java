import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {

    // TODO - JAVA DOC for all methods here

    protected Path argument;

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "ERROR: data parameter is null");
        data.loadData(argument);
    }

    // Constructor
    public AddCmd(String argumentInput){
        super(CommandType.ADD, argumentInput);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "ERROR: argument is null");
        if (argumentInput.length() < 4) {
            System.err.print("ERROR: Not a valid argument");
            return false;
        }

        int len = argumentInput.length();
        String extension = argumentInput.substring(len-4, len);
        boolean returnValue = extension.equals(".csv");
        argument = Paths.get(argumentInput);
        return returnValue;
    }
}
