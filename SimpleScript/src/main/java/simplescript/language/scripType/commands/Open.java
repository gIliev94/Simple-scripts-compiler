package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import simplescript.configurator.ConfigurationConstants;

/**
 * Implementation class for the scripType command - "OPEN".
 * 
 * @author Georgi Iliev
 *
 */
public class Open extends Command {

    protected String program;

    public Open(String program) {
	this.program = program;
    }

    public void execute() throws AWTException, IOException {
	if (program.toLowerCase().startsWith("www.") || program.toLowerCase().endsWith(".exe")) {
	    Runtime system = Runtime.getRuntime();
	    File desktopFolder = new File(ConfigurationConstants.DESKTOP_FOLDER_PATH);
	    system.exec("cmd /c start " + program.toLowerCase(), null, desktopFolder);
	}
    }

    @Override
    public String toString() {
	return "open";
    }

}
