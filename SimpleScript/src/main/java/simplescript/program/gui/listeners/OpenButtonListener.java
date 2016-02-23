package simplescript.program.gui.listeners;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.gui.backbone.OutputArea;
import simplescript.program.utilities.StringConstants;

/**
 * Action listener for "OPEN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class OpenButtonListener extends AbstractButtonListener {

    JFileChooser fileChooser;
    RunButtonListener runButtonListener;

    public OpenButtonListener(OutputArea outputArea, JFileChooser fileChooser, AbstractButtonListener runButtonListener) {
	super(outputArea);

	if (!(runButtonListener instanceof RunButtonListener)) {
	    throw new RuntimeException("Invalid button listener!");
	} else {
	    this.fileChooser = fileChooser;
	    this.runButtonListener = (RunButtonListener) runButtonListener;
	}

    }

    @Override
    public void actionPerformed(ActionEvent e) {

	// Initial validation of a loaded source file
	int dialogState = fileChooser.showOpenDialog(fileChooser);

	if (dialogState == JFileChooser.CANCEL_OPTION) {
	    output.showErr("WARNING", "File selection cancelled!", JOptionPane.WARNING_MESSAGE);
	    return;
	}

	File codeToCompile = fileChooser.getSelectedFile();

	if (!codeToCompile.isFile() || codeToCompile.length() == 0) {
	    output.showErr("INFO", "Not a file or empty file!", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}

	if (!codeToCompile.getName().endsWith(".txt")) {
	    output.showErr("ERROR", "Unauthorized file, only .txt permitted!", JOptionPane.ERROR_MESSAGE);
	    return;
	}

	// Extraction of commands from the source file
	BufferedReader reader = null;
	Vector<String> commandLines = new Vector<String>(16);

	try {
	    String currentLine;
	    reader = new BufferedReader(new FileReader(codeToCompile));

	    while ((currentLine = reader.readLine()) != null) {
		commandLines.add(currentLine);
	    }

	    Object[] separateCommands = new String[commandLines.size()];

	    separateCommands = commandLines.toArray();

	    runButtonListener.separateCommands = separateCommands;

	    output.showOutMsg("File opened: " + StringConstants.quote(codeToCompile.getName()));

	} catch (FileNotFoundException fnfe) {
	    output.showErr("ERROR", "File not found: " + codeToCompile.getName(), JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("File not found");
	} catch (IOException ioe) {
	    output.showErr("ERROR",
		    "Error with file / directory: " + StringConstants.NEWLINE + ioe.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Error with file / directory: " + codeToCompile.getName(), ioe);
	} catch (NullPointerException npe) {
	    output.showErr("ERROR", "Missing internal resource value: " + npe.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Missing internal resource value: " + npe.getLocalizedMessage(), npe);
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
	    } catch (IOException ioe) {
		output.showErr("ERROR",
			"Error with file / directory: " + StringConstants.NEWLINE + ioe.getLocalizedMessage(),
			JOptionPane.ERROR_MESSAGE);
		output.showOutMsg("Compilation failed!");
		SimpleScriptMain.LOG.warn("Error with file / directory: ", ioe);
	    }
	}
    }

}
