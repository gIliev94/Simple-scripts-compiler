package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Text;
import simplescript.program.utilities.StringConstants;

/**
 * Implementation of a processor for scripType command - "TEXT".
 * 
 * @author Georgi Iliev
 *
 */
public class TextProcessor extends CommandProcessor {

    public TextProcessor(String commandStatement) {
	super(commandStatement);
    }

    public Command buildExecutableCommand() throws AWTException, IOException {
	String text = StringConstants.EMPTY_STRING;

	for (int i = 1; i < commandParts.length; i++) {
	    if (i == commandParts.length - 1) {
		text += commandParts[i];
	    } else {
		text += commandParts[i] + StringConstants.WHITESPACE;
	    }

	}

	return new Text(text);
    }

}
