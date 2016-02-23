package simplescript.program.gui.buttons;

import java.util.UUID;

import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.ComponentMetrics;

/**
 * JButton implementation for "OPEN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class OpenButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public OpenButton(String buttonText, ComponentMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.openButtonX, metrics.openButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Opens a source( .txt ) file");
    }

}
