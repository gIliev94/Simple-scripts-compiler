package simplescript.program.gui.listeners;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import simplescript.language.scripType.CommandRuntime;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.CommandNotFirstLineException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.gui.backbone.Canvas;
import simplescript.program.gui.backbone.OutputArea;
import simplescript.program.utilities.StringConstants;

/**
 * Action listener for "COMPILE" button.
 * 
 * @author Georgi Iliev
 *
 */
public class RunButtonListener extends AbstractButtonListener {

    Canvas canvasPanel;
    Object[] separateCommands;

    public RunButtonListener(OutputArea outputArea, Canvas canvasPanel) {
	super(outputArea);
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

	    output.showOutMsg("Successful execution!");

	} catch (IOException ioe) {
	    output.showErr("ERROR",
		    "Error with file / directory: " + StringConstants.NEWLINE + ioe.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Error with file/directory: ", ioe);
	} catch (AWTException awte) {
	    output.showErr("ERROR", "Automation / Threading problem: " + awte.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Automation / Threading problem: ", awte);
	} catch (UnknownCommandException uce) {
	    output.showErr("ERROR", uce.getLocalizedMessage() + StringConstants.NEWLINE + StringConstants.NEWLINE
		    + "/ REOPEN FILE AFTER YOU FIX THE ERROR /", JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	} catch (NullPointerException npe) {
	    output.showErr("ERROR", "Missing / not opened file!", JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("Missing / not opened file!", npe);
	} catch (ArrayIndexOutOfBoundsException aobe) {
	    output.showErr("WARNING", "Empty file, no commands to run!", JOptionPane.WARNING_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("Empty file, no commands to run!", aobe);
	} catch (NumberFormatException nfe) {
	    output.showErr("ERROR", "Illegal format for color, use # prefix!", JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	} catch (IllegalArgumentException iae) {
	    output.showErr("ERROR", iae.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	} catch (CommandFormatException cfe) {
	    output.showErr("ERROR", cfe.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	} catch (CommandNotFirstLineException cnfle) {
	    output.showErr("ERROR", cnfle.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	}
    }

}
