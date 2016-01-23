package simplescript.language.scripType;

/**
 * <h1>Resource class - lists all legal command formats.</h1>
 * <p>
 * </p>
 * 
 * @author Georgi Iliev
 */
public class CommandFormats {

    public static final String CLICK_FORMAT = "^CLICK\\sLEFT$|RIGHT$|LEFTDOUBLE$";
    public static final String MOVE_FORMAT = "^MOVE\\s[0-9]+\\s[0-9]+$";
    public static final String PRESS_FORMAT = "^PRESS\\s\\w+$|\\w+\\+\\w+$";
    public static final String DELAY_FORMAT = "^DELAY\\s[0-9]+$";
    public static final String POINT_FORMAT = "^POINT\\s#[0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]$";
    public static final String LINE_FORMAT = "^LINE\\s#[0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-1]$";
    public static final String OPEN_FORMAT = "^OPEN\\s\\w+\\.\\w+$|\\w+\\.\\w+\\.\\w+$";
    public static final String TEXT_FORMAT = "^TEXT\\s\\w+";

}
