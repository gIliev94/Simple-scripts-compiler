package simplescript.program.utilities;

/**
 * Utility class - commonly used string values as constants.
 * 
 * @author Georgi Iliev
 *
 */
public class StringConstants {

    private StringConstants() {
    }

    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String NEWLINE = System.getProperty("line.separator");

    private static final String DOUBLEQUOTE = "\"";

    /**
     * Quotes a text selection.
     * 
     * @param text
     * @return A quoted version of the parameter text.
     */
    public static String quote(String text) {
	return DOUBLEQUOTE + text + DOUBLEQUOTE;
    }

}
