package simplescript.language.scripType.processors;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.exceptions.WrongCommandException;
import simplescript.program.gui.backbone.Canvas;
import simplescript.program.utilities.StringConstants;

/**
 * Environment class - evaluates and processes commands.
 * 
 * @author Georgi Iliev
 * 
 */
public abstract class CommandProcessor implements ICommandProcessor {

    protected String commandString;
    protected String[] commandParts;
    protected Canvas canvas;

    public CommandProcessor(String commandStatement, Canvas canvasPanel) throws UnknownCommandException {
	if (commandStatement != null && canvasPanel != null) {
	    this.commandString = commandStatement;
	    this.commandParts = commandString.split(StringConstants.WHITESPACE);
	    this.canvas = canvasPanel;
	}
    }

    public CommandProcessor(String commandStatement) throws UnknownCommandException {
	if (commandStatement != null) {
	    this.commandString = commandStatement;
	    this.commandParts = commandString.split(StringConstants.WHITESPACE);
	}
    }

    /**
     * <h1><i>evaluateCommand</i></h1>
     * <p>
     * <p>
     * {@code private static void evaluateCommand(String commandStatement, String command)}
     * </p>
     * Validates a command`s correct position, keyword and format. </p>
     * 
     * @param commandStatement
     *            - the full command statement, the command plus the parameters.
     * @param command
     *            - the command keyword that the statement is supposed to begin
     *            with.
     * @throws CommandFormatException
     * @throws WrongCommandException
     * @throws UnknownCommandException
     */
    private static void evaluateCommand(String commandStatement, String command) throws CommandFormatException,
	    WrongCommandException, UnknownCommandException {

	if (command.equalsIgnoreCase(StringConstants.EMPTY_STRING)) {
	    throw new UnknownCommandException(StringConstants.quote(command) + StringConstants.NEWLINE
		    + "Command should be the first line of text file!");
	}

	if (!Command.isValidCommand(command)) {
	    throw new WrongCommandException(StringConstants.quote(command));
	}

	if (!Command.hasValidCommandFormat(command, commandStatement)) {
	    throw new CommandFormatException(StringConstants.quote(command));
	}

    }

    /**
     * <h1><i>getProcessor</i></h1>
     * <p>
     * <p>
     * {@code public static CommandProcessor getProcessor(String commandString, CanvasActions canvas)}
     * </p>
     * Determines and returns the neccessary processor for the given command,
     * assuming it passes initial validation. </p>
     * 
     * @param commandStatement
     *            - the statement string from user source file.
     * @param canvasPanel
     *            - the current canvas object for painting purposes.
     * @return Appropriate processor for the given command.
     * @throws UnknownCommandException
     * @throws CommandFormatException
     */
    public static CommandProcessor getProcessor(String commandStatement, Canvas canvasPanel)
	    throws UnknownCommandException, CommandFormatException {

	String command = commandStatement.split(StringConstants.WHITESPACE)[0];

	evaluateCommand(commandStatement, command);

	if (command.equalsIgnoreCase(Keywords.LINE)) {
	    return new LineProcessor(commandStatement, canvasPanel);

	} else if (command.equalsIgnoreCase(Keywords.POINT)) {
	    return new PointProcessor(commandStatement, canvasPanel);

	} else if (command.equalsIgnoreCase(Keywords.TEXT)) {
	    return new TextProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.OPEN)) {
	    return new OpenProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.PRESS)) {
	    return new PressProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.DELAY)) {
	    return new DelayProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.CLICK)) {
	    return new ClickProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.MOVE)) {
	    return new MoveProcessor(commandStatement);

	} else {

	    throw new UnknownCommandException(StringConstants.quote(command));
	}
    }

}
