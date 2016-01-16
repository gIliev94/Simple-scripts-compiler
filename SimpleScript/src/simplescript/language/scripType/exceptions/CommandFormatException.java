package simplescript.language.scripType.exceptions;

import java.util.UUID;

/**
 * <h1>Resource class - formats and exception for command format with
 * appropriate message.</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-17
 * @author Georgi Iliev
 * @version 1.0
 */
public class CommandFormatException extends Exception {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public CommandFormatException(String message) {
	super("Wrong format of command: " + message + "\n" + "Make sure the parameters are correctly used!");
    }
}
