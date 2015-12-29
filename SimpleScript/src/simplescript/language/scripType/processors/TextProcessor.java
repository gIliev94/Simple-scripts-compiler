package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Text;
import simplescript.language.scripType.exceptions.UnknownCommandException;

public class TextProcessor extends CommandProcessor {

    public TextProcessor(String commandStatement)
	    throws UnknownCommandException {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	String text = "";

	for (int i = 1; i < commandParts.length; i++) {
	    if (i == commandParts.length - 1) {
		text += commandParts[i];
	    } else {
		text += commandParts[i] + " ";
	    }

	}

	return new Text(text);
    }
}
