package simplescript.program.gui.buttons;

import java.util.UUID;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.FrameMetrics;

/**
 * JButton implementation for "DELETE" button.
 * 
 * @author Georgi Iliev
 *
 */
public class DeleteButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public DeleteButton(String buttonText, FrameMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.deleteButtonX, metrics.deleteButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Deletes source files( tagged with [src] )");
    }

}
