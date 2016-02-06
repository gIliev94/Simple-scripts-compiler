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
import javax.swing.JTextArea;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.gui.backbone.FileChooser;
import simplescript.program.utilities.StringConstants;

/**
 * Action listener for "OPEN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class OpenButtonListener extends AbstractButtonListener {

    FileChooser fileChooser;
    CompileButtonListener compileListener;

    public OpenButtonListener(JTextArea area, FileChooser fileChooser, CompileButtonListener compileListener) {
	super(area);
	this.fileChooser = fileChooser;
	this.compileListener = compileListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	// Initial validation of a loaded source file
	int dialogState = fileChooser.showOpenDialog(fileChooser);

	if (dialogState == JFileChooser.CANCEL_OPTION) {
	    showErr("WARNING", "File selection cancelled!", JOptionPane.WARNING_MESSAGE);
	    return;
	}

	File codeToCompile = fileChooser.getSelectedFile();

	if (!codeToCompile.isFile() || !codeToCompile.getName().endsWith(".txt")) {
	    showErr("ERROR", "Unauthorized file, only .txt permitted!!", JOptionPane.ERROR_MESSAGE);
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

	    compileListener.separateCommands = separateCommands;

	    showOutMsg("File sucessfully opened: " + StringConstants.quote(codeToCompile.getName()));

	} catch (FileNotFoundException fnfe) {
	    showErr("ERROR", "File not found: " + codeToCompile.getName(), JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.info("File not found");
	} catch (IOException ioe) {
	    showErr("ERROR", "Error with file / directory: " + StringConstants.NEWLINE + ioe.getMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Error with file / directory: " + codeToCompile.getName(), ioe);
	} catch (NullPointerException npe) {
	    showErr("ERROR", "Missing internal resource value: " + npe.getMessage(), JOptionPane.ERROR_MESSAGE);
	    showOutMsg("Compilation failed!");
	    SimpleScriptMain.LOG.error("Missing internal resource value: " + npe.getMessage(), npe);
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
	    } catch (IOException ioe) {
		showErr("ERROR", "Error with file / directory: " + StringConstants.NEWLINE + ioe.getMessage(),
			JOptionPane.ERROR_MESSAGE);
		showOutMsg("Compilation failed!");
		SimpleScriptMain.LOG.warn("Error with file / directory: ", ioe);
	    }
	}
    }

}
