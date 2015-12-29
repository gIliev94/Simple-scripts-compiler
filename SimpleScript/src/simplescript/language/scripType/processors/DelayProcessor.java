package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Delay;
import simplescript.language.scripType.exceptions.UnknownCommandException;

public class DelayProcessor extends CommandProcessor {

    public DelayProcessor(String commandStatement)
	    throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Delay(commandParts[1]);
    }
}