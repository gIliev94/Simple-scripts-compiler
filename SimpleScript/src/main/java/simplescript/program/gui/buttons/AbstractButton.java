package simplescript.program.gui.buttons;

import java.awt.Font;
import java.util.UUID;
import javax.swing.JButton;
import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.ComponentMetrics;

/**
 * Abstract model of a button in SimpleScript.
 * 
 * @author Georgi Iliev
 *
 */
public abstract class AbstractButton extends JButton implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    protected AbstractButton(String buttonText, ComponentMetrics metrics, AbstractButtonListener listener) {
	super(buttonText);
	this.setFont(new Font("Tahoma", Font.BOLD, 11));
    }

    public abstract void setAdditionalAttributes();

}
