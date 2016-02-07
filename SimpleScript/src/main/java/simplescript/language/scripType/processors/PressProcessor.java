package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Press;
import simplescript.language.scripType.exceptions.UnknownCommandException;

/**
 * Implementation of a processor for scripType command - "PRESS".
 * 
 * @author Georgi Iliev
 *
 */
public class PressProcessor extends CommandProcessor {

    public PressProcessor(String commandStatement) throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Press(commandParts[1]);
    }

}
