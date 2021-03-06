package simplescript.program.gui.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import simplescript.configurator.ConfigurationConstants;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.gui.backbone.OutputArea;
import simplescript.program.utilities.StringConstants;

/**
 * Action listener for "DELETE" button.
 * 
 * @author Georgi Iliev
 *
 */
public class DeleteButtonListener extends AbstractButtonListener {

    public DeleteButtonListener(OutputArea area) {
	super(area);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {

	    // Cleans up the source files from Desktop,
	    File desktopFolder = new File(ConfigurationConstants.DESKTOP_FOLDER_PATH);
	    Runtime system = Runtime.getRuntime();
	    system.exec("cmd /c del [src]*.txt", null, desktopFolder);

	    output.showOutMsg("Excercise files removed!");
	} catch (IOException ioe) {
	    output.showErr("ERROR", "Error with file / directory: " + StringConstants.NEWLINE + ioe.getLocalizedMessage(),
		    JOptionPane.ERROR_MESSAGE);
	    output.showOutMsg("Deletion failed!");
	    SimpleScriptMain.LOG.error("Error with file / directory: ", ioe);
	}

    }

}
