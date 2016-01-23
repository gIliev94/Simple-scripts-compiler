package simplescript.language.scripType;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.ICommand;
import simplescript.language.scripType.exceptions.UnknownCommandException;

/**
 * <h1>Helper class - encloses all user input commands and runs them in a
 * sequence.</h1>
 * <p>
 * </p>
 * 
 * @author Georgi Iliev
 */
public class CommandRuntime {

    ICommand[] commands;

    public CommandRuntime(ICommand[] userInputCommands) throws UnknownCommandException {
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