package simplescript.language.scripType.exceptions;

import java.util.UUID;

/**
 * Resource class - defines an exception for non-existing command with
 * appropriate message.
 * 
 * @author Georgi Iliev
 * 
 */
public class CommandNotFirstLineException extends Exception {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public CommandNotFirstLineException() {
	super("Command should be the first line of text file!");
    }

}
