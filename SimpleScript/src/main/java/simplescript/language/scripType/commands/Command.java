package simplescript.language.scripType.commands;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import simplescript.language.scripType.CommandFormats;
import simplescript.language.scripType.Keywords;
import simplescript.program.utilities.StringConstants;

/**
 * Environment class - perfroms core validation of a command before it gets
 * scheduled for proccessing.
 * 
 * @author Georgi Iliev
 */
public abstract class Command implements ICommand {

    /**
     * <h1><i>hasValidCommandKeyword</i></h1>
     * <p>
     * <p>
     * {@code public static boolean hasValidCommandKeyword(String testableKeyword)}
     * </p>
     * Evaluates whether the command is in fact valid or not. </p>
     * 
     * @param testableKeyword
     *            - the keyword to be validated as command.
     * @return TRUE if command validation passes, FALSE if it does not.
     */
    public static boolean hasValidCommandKeyword(String testableKeyword) {
	Field[] fields = Keywords.class.getDeclaredFields();

	for (int i = 0; i < fields.length; i++) {
	    if (testableKeyword.equalsIgnoreCase(fields[i].getName())) {
		return true;
	    }
	}

	return false;
    }

    /**
     * <h1><i>hasValidFormat</i></h1>
     * <p>
     * <p>
     * {@code public static boolean hasValidFormat(String testableSt�tement))}
     * </p>
     * Validates the format of a user input command, in order for it to be
     * processed. </p>
     * 
     * @param testableStatement
     *            - the command statement from user source file.
     * @return TRUE if the format is valid, FALSE if it is not.
     * 
     */
    public static boolean hasValidFormat(String testableStatement) {
	String testablePattern = "dummyPattern";
	String testableKeyword = testableStatement.split(StringConstants.WHITESPACE)[0];

	if (testableKeyword.equalsIgnoreCase(Keywords.LINE)) {
	    testablePattern = CommandFormats.LINE_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.POINT)) {
	    testablePattern = CommandFormats.POINT_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.TEXT)) {
	    testablePattern = CommandFormats.TEXT_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.CLICK)) {
	    testablePattern = CommandFormats.CLICK_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.MOVE)) {
	    testablePattern = CommandFormats.MOVE_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.OPEN)) {
	    testablePattern = CommandFormats.OPEN_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.PRESS)) {
	    testablePattern = CommandFormats.PRESS_FORMAT;
	} else if (testableKeyword.equalsIgnoreCase(Keywords.DELAY)) {
	    testablePattern = CommandFormats.DELAY_FORMAT;
	}

	Pattern pattern = Pattern.compile(testablePattern);

	Matcher matcher = pattern.matcher(testableStatement.toUpperCase());

	return matcher.find();
    }

}
