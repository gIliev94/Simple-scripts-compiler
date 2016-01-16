package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Shortcut;
import simplescript.language.scripType.exceptions.UnknownCommandException;

public class ShortcutProcessor extends CommandProcessor {

    public ShortcutProcessor(String commandStatement) throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	return new Shortcut(commandParts[1]);
    }
}
