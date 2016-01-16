package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Move;
import simplescript.language.scripType.exceptions.UnknownCommandException;

public class MoveProcessor extends CommandProcessor {

    public MoveProcessor(String commandStatement) throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Move(commandParts[1], commandParts[2]);
    }
}
