package simplescript.language.scripType.exceptions;

import java.util.UUID;

/**
 * Resource class - defines an exception for non-existing command with
 * appropriate message.
 * 
 * @author Georgi Iliev
 * 
 */
public class UnknownCommandException extends Exception {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public UnknownCommandException(String message) {
	super("There is no such command " + message);
    }

}
