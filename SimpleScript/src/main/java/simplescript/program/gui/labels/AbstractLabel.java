package simplescript.program.gui.labels;

import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import javax.swing.JLabel;

import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.utilities.ComponentMetrics;

/**
 * Abstract model of a label in SimpleScript.
 * 
 * @author Georgi Iliev
 *
 */
public abstract class AbstractLabel extends JLabel implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    protected AbstractLabel(String labelText, ComponentMetrics metrics) {
	super(labelText);
	this.setFont(new Font("Tahoma", Font.BOLD, 12));
	this.setForeground(Color.YELLOW);
    }

    public abstract void setAdditionalAttributes();

}
