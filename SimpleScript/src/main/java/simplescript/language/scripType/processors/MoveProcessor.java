package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Move;

/**
 * Implementation of a processor for the scripType command - "MOVE".
 * 
 * @author Georgi Iliev
 *
 */
public class MoveProcessor extends CommandProcessor {

    public MoveProcessor(String commandStatement) {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Move(commandParts[1], commandParts[2]);
    }

}
