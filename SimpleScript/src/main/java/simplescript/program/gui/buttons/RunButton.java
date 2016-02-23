package simplescript.program.gui.buttons;

import java.util.UUID;

import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.ComponentMetrics;

/**
 * JButton implementation for "RUN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class RunButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public RunButton(String buttonText, ComponentMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.runButtonX, metrics.runButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Compiles and runs code from a source( .txt ) file");
    }

}
