package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Click;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.UnknownCommandException;

public class ClickProcessor extends CommandProcessor {

    public ClickProcessor(String commandStatement)
	    throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Click(commandParts[1]);
    }
}
