package simplescript.language.scripType;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.UnknownCommandException;

/**
 * <h1>Helper class - encloses all user input commands and runs them in a
 * sequence.</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-15
 * @author Georgi Iliev
 * @version 1.0
 */
public class CommandRuntime {

    Command[] commands;

    public CommandRuntime(Command[] userInputCommands) throws UnknownCommandException {
	this.commands = userInputCommands;
    }

    /**
     * <h1><i>run</i></h1>
     * <p>
     * <p>
     * {@code public void run()}
     * </p>
     * Runs all the commands one after the other. </p>
     * 
     * @throws AWTException
     * @throws IOException
     */
    public void run() throws AWTException, IOException {
	if (commands == null)
	    return;
	for (int i = 0; i < commands.length; i++) {
	    commands[i].execute();
	}
    }
}