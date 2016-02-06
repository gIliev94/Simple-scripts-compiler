package simplescript.configurator;

/**
 * Utility class - constants to help set up the different paths and names used.
 * 
 * @author Georgi Iliev
 *
 */
public class ConfigurationConstants {

    private ConfigurationConstants() {
    }

    public static final String CURRENT_USER = System.getenv("username");
    public static final String PROGRAM_FOLDER_PATH = System.getProperty("user.home") + "\\Desktop\\simpleScript\\";
    public static final String DESKTOP_FOLDER_PATH = System.getProperty("user.home") + "\\Desktop\\";

}
