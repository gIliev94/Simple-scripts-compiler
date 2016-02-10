package simplescript.language.scripType.exceptions;

import java.util.UUID;

/**
 * Resource class - defines an exception for invalid command with appropriate
 * message.
 * 
 * @author Georgi Iliev
 * 
 */
public class WrongCommandException extends UnknownCommandException {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public WrongCommandException(String commandKeyword) {
	super("Wrong command keyword: " + commandKeyword);
    }

}
