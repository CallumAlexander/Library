public class AddCmd extends LibraryCommand {

    @Override
    public void execute(LibraryData data) {

    }

    public AddCmd(String argumentInput){
        super(CommandType.ADD, argumentInput);

    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        //todo - not implemented fully, this is what the ide generated
        return super.parseArguments(argumentInput);
    }
}
