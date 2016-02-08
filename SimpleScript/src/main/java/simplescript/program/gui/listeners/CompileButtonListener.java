package simplescript.program.gui.listeners;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import simplescript.language.scripType.CommandRuntime;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.gui.backbone.Canvas;
import simplescript.program.utilities.StringConstants;

/**
 * Action listener for "COMPILE" button.
 * 
 * @author Georgi Iliev
 *
 */
public class CompileButtonListener extends AbstractButtonListener {

    Canvas canvasPanel;
    Object[] separateCommands;

    public CompileButtonListener(JTextArea area, Canvas canvasPanel) {
	super(area);
	this.canvasPanel = canvasPanel;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	try {

	    CommandProcessor processor;
	    Command[] executableCommands = new Command[separateCommands.length];

	    // Processing and building commands
	    String commandStatement = StringConstants.EMPTY_STRING;

	    for (int i = 0; i < separateCommands.length; i++) {
		commandStatement = (String) separateCommands[i];

		processor = CommandProcessor.getProcessor(commandStatement, canvasPanel);

		CommandProcessor.evaluateCommand(commandStatement);

		executableCommands[i] = processor.buildExecutableCommand();
	    }

	    // Running commands
	    CommandRuntime runtime = CommandRuntime.getInstance();
	    runtime.runScript(executableCommands);

	    showOutMsg("Successful execution!");

	} catch (IOException ioe) {
	    showErr("ERROR", "Error with file / directory: " + StringConstants.NEWLINE + ioe.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Error with file/directory: ", ioe);
	} catch (AWTException awte) {
	    showErr("ERROR", "Automation / Threading problem: " + awte.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Automation / Threading problem: ", awte);
	} catch (UnknownCommandException uce) {
	    showErr("ERROR", uce.getLocalizedMessage() + StringConstants.NEWLINE + StringConstants.NEWLINE
		    + "/ REOPEN FILE AFTER YOU FIX THE ERROR /", JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	} catch (NullPointerException npe) {
	    showErr("ERROR", "Missing / not opened file!", JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("Missing / not opened file!", npe);
	} catch (ArrayIndexOutOfBoundsException aobe) {
	    showErr("WARNING", "Empty file, no commands to run!", JOptionPane.WARNING_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("Empty file, no commands to run!", aobe);
	} catch (NumberFormatException nfe) {
	    showErr("ERROR", "Illegal format for color, use # prefix!", JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	} catch (IllegalArgumentException iae) {
	    showErr("ERROR", iae.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	} catch (CommandFormatException cfe) {
	    showErr("ERROR", cfe.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	}
    }

}
