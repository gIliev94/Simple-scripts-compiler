package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Field;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.exceptions.WrongCommandException;

/**
 * <h1>Environment class - validates and executes a command</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-27
 * @author Georgi Iliev
 * @version 1.4
 */
public abstract class Command {

    /**
     * <h1><i>execute</i></h1>
     * <p>
     * <p>
     * {@code public abstract void execute()}
     * </p>
     * Performs the neccessary low level opearations(object creation, settings,
     * validations) to execute the built command. </p>
     * 
     * @throws AWTException
     * @throws IOException
     */
    public abstract void execute() throws AWTException, IOException;

    public abstract String toString();

    /**
     * <h1><i>getCommandFormat</i></h1>
     * <p>
     * <p>
     * {@code public abstract String getCommandFormat()}
     * </p>
     * Gets the corresponding format for the command. ( based on Regular
     * Expression ) </p>
     * 
     * @return A string representing the format.
     */
    public abstract String getCommandFormat();

    /**
     * <h1><i>isIncorrectCommand</i></h1>
     * <p>
     * <p>
     * {@code public static boolean isIncorrectCommand(String statement)}
     * </p>
     * Evaluates whether the command is in fact incorrect or not. </p>
     * 
     * @param statement
     *            - the statement suspected to be incorrect user command.
     * @return TRUE if command validation passes, FALSE if it does not.
     * @throws WrongCommandException
     */
    public static boolean isIncorrectCommand(String statement)
	    throws WrongCommandException {
	Field[] fields = Keywords.class.getDeclaredFields();
	for (int i = 0; i < fields.length; i++) {
	    if (statement.contains(fields[i].getName().toLowerCase())
		    && !(statement.equalsIgnoreCase(fields[i].getName())))
		return true;
	}
	return false;
    }
}
