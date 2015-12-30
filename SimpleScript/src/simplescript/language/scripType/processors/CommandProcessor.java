package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.exceptions.WrongCommandException;
import simplescript.program.gui.CanvasActions;

/**
 * <h1>Environment class - validates and processes commands</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-25
 * @author Georgi Iliev
 * @version 1.6
 */
public abstract class CommandProcessor {

    protected String commandString;
    protected String[] commandParts;
    protected CanvasActions processorCanvas;

    public CommandProcessor(String commandStatement, CanvasActions canvas)
	    throws UnknownCommandException {
	if (commandStatement != null && canvas != null) {
	    this.commandString = commandStatement;
	    this.commandParts = commandString.split(" ");
	    this.processorCanvas = canvas;
	}
    }

    public CommandProcessor(String commandStatement)
	    throws UnknownCommandException {
	if (commandStatement != null) {
	    this.commandString = commandStatement;
	    this.commandParts = commandString.split(" ");
	}
    }

    /**
     * <h1><i>buildExecutableCommand</i></h1>
     * <p>
     * <p>
     * {@code public abstract Command buildExecutableCommand()}
     * </p>
     * Performs the neccessary low level opearations(object creation, settings,
     * validations) to build a ready-to-execute command. </p>
     * 
     * @return Command object containing a command, that is ready to be executed
     *         in runtime.
     * @throws AWTException
     * @throws IOException
     */
    public abstract Command buildExecutableCommand() throws AWTException,
	    IOException;

    /**
     * <h1><i>validateCommand</i></h1>
     * <p>
     * <p>
     * {@code private static void validateCommand(String commandStatement, String command)}
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
    private static void validateCommand(String commandStatement, String command)
	    throws CommandFormatException, WrongCommandException,
	    UnknownCommandException {

	if (command.equalsIgnoreCase("")) {
	    throw new UnknownCommandException("\"" + command + "\""
		    + "\nCommand should be the first line of text file!");
	}

	if (!Command.isValidCommand(command)) {
	    throw new WrongCommandException("\"" + command + "\"");
	}

	if (!Command.hasValidCommandFormat(command, commandStatement)) {
	    throw new CommandFormatException("\"" + command + "\"");
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
     * @param canvas
     *            - the current canvas object for painting purposes.
     * @return Appropriate processor for the given command.
     * @throws UnknownCommandException
     * @throws CommandFormatException
     */
    public static CommandProcessor getProcessor(String commandStatement,
	    CanvasActions canvas) throws UnknownCommandException,
	    CommandFormatException {

	String command = commandStatement.split(" ")[0];

	validateCommand(commandStatement, command);

	if (command.equalsIgnoreCase(Keywords.LINE)) {
	    return new LineProcessor(commandStatement, canvas);

	} else if (command.equalsIgnoreCase(Keywords.POINT)) {
	    return new PointProcessor(commandStatement, canvas);

	} else if (command.equalsIgnoreCase(Keywords.TEXT)) {
	    return new TextProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.OPEN)) {
	    return new OpenProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.PRESS)) {
	    return new ShortcutProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.DELAY)) {
	    return new DelayProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.CLICK)) {
	    return new ClickProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.MOVE)) {
	    return new MoveProcessor(commandStatement);

	} else {
	    throw new UnknownCommandException("\"" + command + "\"");

	}
    }
}
