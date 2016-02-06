package simplescript.program.gui;

import javax.swing.JOptionPane;
import simplescript.program.utilities.StringConstants;

/**
 * Executor thread of the Swing form.
 * 
 * @author Georgi Iliev
 *
 */
public class MainExecutorRunnable implements Runnable {

    public void run() {
	try {
	    SimpleScriptMain window = new SimpleScriptMain();
	    window.frame.setVisible(true);
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, StringConstants.NEWLINE + "Unexpected error: " + e.getMessage(),
		    "ERROR", JOptionPane.ERROR_MESSAGE);
	    SimpleScriptMain.LOG.error("Unexpected error: " + e.getMessage(), e);
	}
    }

}
