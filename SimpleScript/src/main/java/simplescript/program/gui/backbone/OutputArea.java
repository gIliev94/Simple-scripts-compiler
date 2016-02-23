package simplescript.program.gui.backbone;

import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.utilities.ComponentMetrics;
import simplescript.program.utilities.StringConstants;

/**
 * JTextArea implementation for resulting output of script execution.
 * 
 * @author Georgi Iliev
 *
 */
public class OutputArea extends JTextArea implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public OutputArea(ComponentMetrics metrics, Canvas canvasPanel) {
	this.setBounds(metrics.textAreaX, metrics.textAreaY, metrics.textAreaWidth, metrics.textAreaHeight);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(metrics.textAreaX, metrics.textAreaY, metrics.textAreaWidth, metrics.textAreaHeight);
	canvasPanel.add(scrollPane);
	scrollPane.setViewportView(this);
	setAdditionalAttributes();
    }

    public void setAdditionalAttributes() {
	this.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	this.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 14));
	this.setToolTipText("Shows status of current execution");
	this.setEditable(false);
	this.setFocusable(false);
    }

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
	this.setText(StringConstants.NEWLINE + message);

	Color visualAid = null;

	if (message.toLowerCase().contains("success")) {
	    visualAid = Color.GREEN;
	} else if (message.toLowerCase().contains("fail")) {
	    visualAid = Color.PINK;
	}

	this.setBackground(visualAid);
    }

}
