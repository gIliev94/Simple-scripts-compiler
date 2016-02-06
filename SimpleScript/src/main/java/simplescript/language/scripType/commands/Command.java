package simplescript.language.scripType.commands;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import simplescript.language.scripType.CommandFormats;
import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.exceptions.WrongCommandException;

/**
 * Environment class - perfroms core validation of a command before it gets
 * scheduled for proccessing.
 * 
 * @author Georgi Iliev
 */
public abstract class Command implements ICommand {

    /**
     * <h1><i>isValidCommand</i></h1>
     * <p>
     * <p>
     * {@code public static boolean isValidCommand(String statement)}
     * </p>
     * Evaluates whether the command is in fact valid or not. </p>
     * 
     * @param statement
     *            - the statement suspected to be invalid user command.
     * @return TRUE if command validation passes, FALSE if it does not.
     * @throws WrongCommandException
     */
    public static boolean isValidCommand(String statement) throws WrongCommandException {
	Field[] fields = Keywords.class.getDeclaredFields();
	for (int i = 0; i < fields.length; i++) {
	    if (statement.equalsIgnoreCase(fields[i].getName()))
		return true;
	}
	return false;
    }

    /**
     * <h1><i>hasValidCommandFormat</i></h1>
     * <p>
     * <p>
     * {@code public static boolean hasValidCommandFormat(String testableClassname,String testableSt�tement))}
     * </p>
     * Validates the format of a user input command, in order for it to be
     * processed. </p>
     * 
     * @param testableClassname
     *            - the name of the class desribing the suspected user command.
     * @param testableSt
     *            �tement - the command statement from user source file.
     * @return TRUE if the format is valid, FALSE if it is not.
     */
    public static boolean hasValidCommandFormat(String testableClassname, String testableStatement) {
	String testablePattern = "dummyPattern";

	if (testableClassname.equalsIgnoreCase(Keywords.LINE)) {
	    testablePattern = CommandFormats.LINE_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.POINT)) {
	    testablePattern = CommandFormats.POINT_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.TEXT)) {
	    testablePattern = CommandFormats.TEXT_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.CLICK)) {
	    testablePattern = CommandFormats.CLICK_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.MOVE)) {
	    testablePattern = CommandFormats.MOVE_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.OPEN)) {
	    testablePattern = CommandFormats.OPEN_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.PRESS)) {
	    testablePattern = CommandFormats.PRESS_FORMAT;
	} else if (testableClassname.equalsIgnoreCase(Keywords.DELAY)) {
	    testablePattern = CommandFormats.DELAY_FORMAT;
	}

	Pattern pattern = Pattern.compile(testablePattern);

	Matcher matcher = pattern.matcher(testableStatement.toUpperCase());

	return matcher.find();
    }

}
