package simplescript.language.scripType.processors;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.CommandNotFirstLineException;
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

    protected Canvas canvas;
    protected String[] commandParts;

    public CommandProcessor(String commandStatement, Canvas canvasPanel) {
	if (commandStatement != null && canvasPanel != null) {
	    this.commandParts = commandStatement.split(StringConstants.WHITESPACE);
	    this.canvas = canvasPanel;
	}
    }

    public CommandProcessor(String commandStatement) {
	if (commandStatement != null) {
	    this.commandParts = commandStatement.split(StringConstants.WHITESPACE);
	}
    }

    /**
     * <h1><i>evaluateCommand</i></h1>
     * <p>
     * <p>
     * {@code public static void evaluateCommand(String commandStatement, String command)}
     * </p>
     * Validates a command`s correct position, keyword and format. </p>
     * 
     * @param commandStatement
     *            - the full command statement, the command plus the parameters.
     * @param commandKeyword
     *            - the command keyword that the statement is supposed to begin
     *            with.
     * @throws CommandFormatException
     * @throws WrongCommandException
     * @throws CommandNotFirstLineException
     */
    public static void evaluateCommand(String commandStatement) throws CommandFormatException, WrongCommandException,
	    UnknownCommandException, CommandNotFirstLineException {
	String commandKeyword = commandStatement.split(StringConstants.WHITESPACE)[0];

	if (commandKeyword.equalsIgnoreCase(StringConstants.EMPTY_STRING)) {
	    throw new CommandNotFirstLineException();
	}

	if (!Command.hasValidKeyword(commandKeyword)) {
	    throw new WrongCommandException(StringConstants.quote(commandKeyword));
	}

	if (!Command.hasValidFormat(commandStatement)) {
	    throw new CommandFormatException(StringConstants.quote(commandKeyword));
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
     * 
     */
    public static CommandProcessor getProcessor(String commandStatement, Canvas canvasPanel) {
	String commandKeyword = commandStatement.split(StringConstants.WHITESPACE)[0];

	if (commandKeyword.equalsIgnoreCase(Keywords.LINE)) {
	    return new LineProcessor(commandStatement, canvasPanel);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.POINT)) {
	    return new PointProcessor(commandStatement, canvasPanel);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.TEXT)) {
	    return new TextProcessor(commandStatement);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.OPEN)) {
	    return new OpenProcessor(commandStatement);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.PRESS)) {
	    return new PressProcessor(commandStatement);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.DELAY)) {
	    return new DelayProcessor(commandStatement);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.CLICK)) {
	    return new ClickProcessor(commandStatement);

	} else if (commandKeyword.equalsIgnoreCase(Keywords.MOVE)) {
	    return new MoveProcessor(commandStatement);

	} else {
	    return null;
	}
    }

}
