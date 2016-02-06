package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Open;
import simplescript.language.scripType.exceptions.UnknownCommandException;

/**
 * Implementation of a processor for the scripType command - "OPEN".
 * 
 * @author Georgi Iliev
 *
 */
public class OpenProcessor extends CommandProcessor {

    public OpenProcessor(String commandStatement) throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Open(commandParts[1]);
    }

}
