package simplescript.language.scripType.exceptions;

import java.util.UUID;
import simplescript.program.utilities.StringConstants;

/**
 * Resource class - defines an exception for invalid command format with
 * appropriate message.
 * 
 * @author Georgi Iliev
 * 
 */
public class CommandFormatException extends Exception {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public CommandFormatException(String commandKeyword) {
	super("Wrong format of command: " + commandKeyword + StringConstants.NEWLINE
		+ "Make sure the parameters are correct!");
    }

}
