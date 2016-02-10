package simplescript.program.gui.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import simplescript.program.utilities.StringConstants;

/**
 * Abstract model of a button action listener in SimpleScript.
 * 
 * @author Georgi Iliev
 *
 */
public abstract class AbstractButtonListener implements ActionListener {

    protected JTextArea area;

    public AbstractButtonListener(JTextArea area) {
	this.area = area;
    }

    public abstract void actionPerformed(ActionEvent e);

    /**
     * <h1><i>showErr</i></h1>
     * <p>
     * {@code  public void showErr(String caption, String message, int warnOrError)}
     * </p>
     * <p>
     * Formats and propagates and error or a warning in a popup pane.
     * </p>
     * 
     * @param caption
     *            - shows the type(warn/err) of the message to the user.
     * @param message
     *            - the message to be shown.
     * @param warnOrError
     *            - constant value diferentiating between WARNING and ERROR.
     */
    public void showErr(String caption, String message, int warnOrError) {
	JOptionPane.showMessageDialog(null, StringConstants.NEWLINE + message, caption, warnOrError);
    }

    /**
     * <h1><i>showOutMsg</i></h1>
     * <p>
     * {@code public void showOutMsg(String message)}
     * </p>
     * <p>
     * Prints appropriate message to the output area.
     * </p>
     * 
     * @param message
     *            - the message to be shown.
     */
    public void showOutMsg(String message) {
	area.setText(StringConstants.NEWLINE + message);

	Color visualAid = null;

	if (message.contains("sucessfull")) {
	    visualAid = Color.GREEN;
	} else if (message.contains("failed")) {
	    visualAid = Color.PINK;
	}

	area.setBackground(visualAid);
    }

}
