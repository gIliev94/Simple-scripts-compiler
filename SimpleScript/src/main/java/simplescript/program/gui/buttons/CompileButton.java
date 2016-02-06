package simplescript.program.gui.buttons;

import java.util.UUID;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.utilities.FrameMetrics;

/**
 * JButton implementation for "RUN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class CompileButton extends AbstractButton {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public CompileButton(String buttonText, FrameMetrics metrics, AbstractButtonListener listener) {
	super(buttonText, metrics, listener);
	this.setBounds(metrics.compileButtonX, metrics.compileButtonY, metrics.buttonWidth, metrics.buttonHeight);
	this.addActionListener(listener);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Compiles and runs code from a source( .txt ) file");
    }

}
