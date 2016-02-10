package simplescript.program.gui;

import javax.swing.JOptionPane;

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
	} catch (RuntimeException re) {
	    JOptionPane.showMessageDialog(null, re.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    SimpleScriptMain.LOG.error("Unexpected error: ", re);
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getLocalizedMessage(), "ERROR",
		    JOptionPane.ERROR_MESSAGE);
	    SimpleScriptMain.LOG.error("Unexpected error: " + e.getMessage(), e);
	}
    }

}
