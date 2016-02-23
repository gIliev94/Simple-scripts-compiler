package simplescript.program.gui.buttons;

import java.util.UUID;

import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.ComponentMetrics;

/**
 * JButton implementation for "CLEAR" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ClearButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public ClearButton(String buttonText, ComponentMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.clearButtonX, metrics.clearButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Clears the canvas");
    }

}
