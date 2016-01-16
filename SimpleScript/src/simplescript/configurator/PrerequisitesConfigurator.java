package simplescript.configurator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import simplescript.program.utilities.KeyMapper;
import simplescript.program.utilities.RobotDelays;

/**
 * Configuration class - sets up the environment.
 * 
 * @author Georgi Iliev
 *
 */
public class PrerequisitesConfigurator {

    private static final Logger LOG = Logger.getLogger(PrerequisitesConfigurator.class);

    private static BufferedWriter filewriter;
    private static Robot typewriter;
    private static String currentUser;
    private static String desktopFolderPath;
    private static char[] userCharacters;

    private static final int NUMBER_OF_TASKS = 5;

    /**
     * <h1><i>setup</i></h1>
     * <p>
     * <p>
     * {@code public static void setup()}
     * </p>
     * Executes all the necessary configuration methods in a sequence.</p>
     */
    public static void setup() {
	try {

	    // Makes sure the Notepad Save directory is DESKTOP
	    doPrecleanup();
	    initializeSetupPrerequisites();
	    saveAs();
	    typePathToUsers();
	    typeUsername();
	    typeDesktop();
	    typeFileName();
	    confirm();

	    // Source files creation
	    createSrcFiles();

	} catch (IOException ioe) {
	    showErr("ERROR", "Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
		    JOptionPane.ERROR_MESSAGE);
	} catch (AWTException awte) {
	    showErr("ERROR", "Automation\\Threading problem: " + awte.getMessage(), JOptionPane.ERROR_MESSAGE);
	    LOG.error(awte.getMessage(), awte);
	} catch (Exception e) {
	    showErr("ERROR", "Unexpected error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
	    LOG.error(e.getMessage(), e);
	} finally {
	    try {
		cleanup();
	    } catch (IOException ioe) {
		showErr("ERROR", "Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
			JOptionPane.ERROR_MESSAGE);
	    }
	}
    }

    /**
     * <h1><i>doPrecleanup</i></h1>
     * <p>
     * <p>
     * {@code private static void doPrecleanup()}
     * </p>
     * Cleans files from previous runs of the application.
     * 
     * @throws IOException
     */
    private static void doPrecleanup() throws IOException {
	Runtime system = Runtime.getRuntime();
	File desktopFolder = new File(ConfigurationConstants.DESKTOP_FOLDER_PATH);
	system.exec("cmd /c del [src]*.txt ", null, desktopFolder);
    }

    /**
     * <h1><i>initializeSetupPrerequisites</i></h1>
     * <p>
     * <p>
     * {@code private static void initializeSetupPrerequisites()}
     * </p>
     * Initializes all the prerequisites - opens up Notepad, sets up the Robot,
     * environment variables, properties..</p>
     * 
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */
    private static void initializeSetupPrerequisites() throws IOException, AWTException, InterruptedException {
	currentUser = ConfigurationConstants.CURRENT_USER;
	desktopFolderPath = ConfigurationConstants.DESKTOP_FOLDER_PATH;

	Runtime system = Runtime.getRuntime();
	// File desktopFolder = new File(desktopFolderPath);
	// system.exec("cmd /c del a.txt ", null, desktopFolder);
	system.exec("cmd /c start notepad.exe");

	typewriter = new Robot();
	typewriter.setAutoDelay(RobotDelays.DOUBLECLICK_DELAY);
	typewriter.setAutoWaitForIdle(true);

	userCharacters = currentUser.toCharArray();
    }

    /**
     * <h1><i>saveAs</i></h1>
     * <p>
     * <p>
     * {@code private static void saveAs()}
     * </p>
     * Performs saving of the file. (equivalent to pressing "Ctrl+S" shortcut on
     * keyboard)</p>
     */
    private static void saveAs() {
	typewriter.delay(RobotDelays.INITIAL_DELAY);
	typewriter.keyPress(KeyEvent.VK_CONTROL);
	typewriter.keyPress(KeyEvent.VK_S);
	typewriter.keyRelease(KeyEvent.VK_S);
	typewriter.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <h1><i>typePathToUsers</i></h1>
     * <p>
     * <p>
     * {@code private static void typePathToUsers()}
     * </p>
     * Performs typing of the path to Windows`s Users directory.</p>
     */
    private static void typePathToUsers() {
	typewriter.keyPress(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_C);
	typewriter.keyRelease(KeyEvent.VK_C);
	typewriter.keyRelease(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_SEMICOLON);
	typewriter.keyRelease(KeyEvent.VK_SEMICOLON);
	typewriter.keyRelease(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_BACK_SLASH);
	typewriter.keyRelease(KeyEvent.VK_BACK_SLASH);
	typewriter.keyPress(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_U);
	typewriter.keyRelease(KeyEvent.VK_U);
	typewriter.keyRelease(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_S);
	typewriter.keyRelease(KeyEvent.VK_S);
	typewriter.keyPress(KeyEvent.VK_E);
	typewriter.keyRelease(KeyEvent.VK_E);
	typewriter.keyPress(KeyEvent.VK_R);
	typewriter.keyRelease(KeyEvent.VK_R);
	typewriter.keyPress(KeyEvent.VK_S);
	typewriter.keyRelease(KeyEvent.VK_S);
	typewriter.keyPress(KeyEvent.VK_BACK_SLASH);
	typewriter.keyRelease(KeyEvent.VK_BACK_SLASH);
    }

    /**
     * <h1><i>typeUsername</i></h1>
     * <p>
     * <p>
     * {@code private static void typeUserame()}
     * </p>
     * Performs typing of the current user`s username.</p>
     */
    private static void typeUsername() {
	int key = 0;
	for (int i = 0; i < userCharacters.length; i++) {
	    key = KeyMapper.retrieveKey(userCharacters[i]);
	    if (Character.isUpperCase(userCharacters[i])) {
		typewriter.keyPress(KeyEvent.VK_SHIFT);
		typewriter.keyPress(key);
		typewriter.keyRelease(key);
		typewriter.keyRelease(KeyEvent.VK_SHIFT);
	    } else {
		typewriter.keyPress(key);
		typewriter.keyRelease(key);
	    }
	}
    }

    /**
     * <h1><i>typeDesktop</i></h1>
     * <p>
     * <p>
     * {@code private static void typeDesktop()}
     * </p>
     * Performs typing of the string "Desktop".</p>
     */
    private static void typeDesktop() {
	typewriter.keyPress(KeyEvent.VK_BACK_SLASH);
	typewriter.keyRelease(KeyEvent.VK_BACK_SLASH);
	typewriter.keyPress(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_D);
	typewriter.keyRelease(KeyEvent.VK_D);
	typewriter.keyRelease(KeyEvent.VK_SHIFT);
	typewriter.keyPress(KeyEvent.VK_E);
	typewriter.keyRelease(KeyEvent.VK_E);
	typewriter.keyPress(KeyEvent.VK_S);
	typewriter.keyRelease(KeyEvent.VK_S);
	typewriter.keyPress(KeyEvent.VK_K);
	typewriter.keyRelease(KeyEvent.VK_K);
	typewriter.keyPress(KeyEvent.VK_T);
	typewriter.keyRelease(KeyEvent.VK_T);
	typewriter.keyPress(KeyEvent.VK_O);
	typewriter.keyRelease(KeyEvent.VK_O);
	typewriter.keyPress(KeyEvent.VK_P);
	typewriter.keyRelease(KeyEvent.VK_P);
    }

    /**
     * <h1><i>typeFileName</i></h1>
     * <p>
     * <p>
     * {@code private static void typeFileName()}
     * </p>
     * Performs typing of the dummy file name.</p>
     */
    private static void typeFileName() {
	typewriter.keyPress(KeyEvent.VK_BACK_SLASH);
	typewriter.keyRelease(KeyEvent.VK_BACK_SLASH);
	typewriter.keyPress(KeyEvent.VK_A);
	typewriter.keyRelease(KeyEvent.VK_A);
	typewriter.keyPress(KeyEvent.VK_PERIOD);
	typewriter.keyRelease(KeyEvent.VK_PERIOD);
	typewriter.keyPress(KeyEvent.VK_T);
	typewriter.keyRelease(KeyEvent.VK_T);
	typewriter.keyPress(KeyEvent.VK_X);
	typewriter.keyRelease(KeyEvent.VK_X);
	typewriter.keyPress(KeyEvent.VK_T);
	typewriter.keyRelease(KeyEvent.VK_T);
    }

    /**
     * <h1><i>confirm</i></h1>
     * <p>
     * <p>
     * {@code private static void confirm()}
     * </p>
     * Performs confirm operation. ( equivalent to pressing "Enter" key from the
     * keyboard ) </p>
     */
    private static void confirm() {
	typewriter.keyPress(KeyEvent.VK_ENTER);
	typewriter.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * <h1><i>createSrcFiles</i></h1>
     * <p>
     * <p>
     * {@code private static void createSrcFiles()}
     * </p>
     * Automates creation of source text files for the user. Abides to the
     * naming rules for the sources. </p>
     * 
     * @throws IOException
     */
    private static void createSrcFiles() throws IOException {
	for (int i = 1; i <= NUMBER_OF_TASKS; i++) {
	    File file = new File(desktopFolderPath, "[src]TASK_" + i + ".txt");
	    filewriter = new BufferedWriter(new FileWriter(file));
	}
    }

    /**
     * <h1><i>cleanup</i></h1>
     * <p>
     * <p>
     * {@code private static void cleanup()}
     * </p>
     * Performs cleanup tasks - closing streams, deleting files etc... </p>
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    private static void cleanup() throws IOException {
	File desktopFolder = new File(desktopFolderPath);
	Runtime system = Runtime.getRuntime();

	system.exec("cmd /c del a.txt ", null, desktopFolder);

	typewriter.keyPress(KeyEvent.VK_ALT);
	typewriter.keyPress(KeyEvent.VK_F4);
	typewriter.keyRelease(KeyEvent.VK_F4);
	typewriter.keyRelease(KeyEvent.VK_ALT);

	filewriter.close();
    }

    /**
     * <h1><i>showErr</i></h1>
     * <p>
     * {@code  private void showErr(String caption, String message, int warnOrError)}
     * </p>
     * <p>
     * Formats and propagates and error or a warning in a popup pane.
     * </p>
     * 
     * @param caption
     *            - shows the type(warn/err) of the message to the user.
     * @param message
     *            - the message to be shown.
     * @param warnOrError
     *            - constant value diferentiating between WARNING and ERROR.
     */
    private static void showErr(String caption, String message, int warnOrError) {
	JOptionPane.showMessageDialog(null, ConfigurationConstants.NEWLINE + message, caption, warnOrError);
    }
}
