package simplescript.language.scripType.exceptions;

import java.util.UUID;

/**
 * Resource class - defines an exception for incorrect command with appropriate
 * message.
 * 
 * @author Georgi Iliev
 * 
 */
public class WrongCommandException extends UnknownCommandException {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public WrongCommandException(String message) {
	super("Wrong command statement: " + message);
    }

}
