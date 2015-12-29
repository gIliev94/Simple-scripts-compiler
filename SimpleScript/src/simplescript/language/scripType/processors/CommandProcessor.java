package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.commands.Click;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Delay;
import simplescript.language.scripType.commands.Line;
import simplescript.language.scripType.commands.Move;
import simplescript.language.scripType.commands.Open;
import simplescript.language.scripType.commands.Point;
import simplescript.language.scripType.commands.Shortcut;
import simplescript.language.scripType.commands.Text;
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
     * <h1><i>checkCommandFormat</i></h1>
     * <p>
     * <p>
     * {@code private static boolean checkCommandFormat(Command testableCommandClass,
	    String testableCommandStàtement)}
     * </p>
     * Validates the format of a user input command, in order for it to be
     * processed. </p>
     * 
     * @param testableCommandClass
     *            - the class desribing the suspected user command.
     * @param testableCommandStàtement
     *            - the command statement from user source file.
     * @return TRUE if the format is correct, FALSE if it is not.
     */
    private static boolean checkCommandFormat(Command testableCommandClass,
	    String testableCommandStàtement) {
	String testablePattern = testableCommandClass.getCommandFormat();
	Pattern pattern = Pattern.compile(testablePattern);
	Matcher matcher = pattern.matcher(testableCommandStàtement
		.toUpperCase());
	return matcher.find();
    }

    /**
     * <h1><i>getProcessor</i></h1>
     * <p>
     * <p>
     * {@code public static CommandProcessor getProcessor(String commandString, CanvasActions canvas)}
     * </p>
     * Determines and returns the neccessary processor for the given command.
     * </p>
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

	if (command.equalsIgnoreCase(Keywords.LINE)) {

	    if (!checkCommandFormat(new Line(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new LineProcessor(commandStatement, canvas);

	} else if (command.equalsIgnoreCase(Keywords.POINT)) {

	    if (!checkCommandFormat(new Point(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new PointProcessor(commandStatement, canvas);

	} else if (command.equalsIgnoreCase(Keywords.TEXT)) {

	    if (!checkCommandFormat(new Text(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new TextProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.OPEN)) {

	    if (!checkCommandFormat(new Open(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new OpenProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.PRESS)) {

	    if (!checkCommandFormat(new Shortcut(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new ShortcutProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.DELAY)) {

	    if (!checkCommandFormat(new Delay(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new DelayProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.CLICK)) {

	    if (!checkCommandFormat(new Click(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new ClickProcessor(commandStatement);

	} else if (command.equalsIgnoreCase(Keywords.MOVE)) {

	    if (!checkCommandFormat(new Move(), commandStatement))
		throw new CommandFormatException("\"" + command + "\"");
	    return new MoveProcessor(commandStatement);

	} else {

	    if (Command.isIncorrectCommand(command)) {
		throw new WrongCommandException("\"" + command + "\"");
	    }
	    if (command.equalsIgnoreCase("")) {
		throw new UnknownCommandException("\"" + command + "\""
			+ "\nCommand should be the first line of text file!");
	    }
	    throw new UnknownCommandException("\"" + command + "\"");
	}
    }
}
