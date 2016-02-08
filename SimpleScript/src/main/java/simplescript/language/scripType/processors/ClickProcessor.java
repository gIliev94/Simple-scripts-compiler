package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Click;
import simplescript.language.scripType.commands.Command;

/**
 * Implementation of a processor for scripType command - "CLICK".
 * 
 * @author Georgi Iliev
 *
 */
public class ClickProcessor extends CommandProcessor {

    public ClickProcessor(String commandStatement) {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Click(commandParts[1]);
    }

}
