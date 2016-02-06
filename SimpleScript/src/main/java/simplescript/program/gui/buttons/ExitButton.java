package simplescript.program.gui.buttons;

import java.util.UUID;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.FrameMetrics;

/**
 * JButton implementation for "EXIT" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ExitButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public ExitButton(String buttonText, FrameMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.backButtonX, metrics.backButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Closes program immediately");
    }

}
