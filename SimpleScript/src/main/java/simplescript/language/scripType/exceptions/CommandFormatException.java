package simplescript.language.scripType.exceptions;

import java.util.UUID;
import simplescript.program.utilities.StringConstants;

/**
 * Resource class - defines an exception for command format with appropriate
 * message.
 * 
 * @author Georgi Iliev
 * 
 */
public class CommandFormatException extends Exception {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public CommandFormatException(String message) {
	super("Wrong format of command: " + message + StringConstants.NEWLINE
		+ "Make sure the parameters are correctly used!");
    }

}
