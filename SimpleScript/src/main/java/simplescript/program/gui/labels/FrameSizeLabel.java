package simplescript.program.gui.labels;

import java.util.UUID;

import simplescript.program.utilities.ComponentMetrics;

/**
 * JLabel implementation for frame size label.
 * 
 * @author Georgi Iliev
 *
 */
public class FrameSizeLabel extends AbstractLabel {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public FrameSizeLabel(String labelText, ComponentMetrics metrics) {
	super(labelText, metrics);
	this.setBounds(metrics.framesizeLabelX, metrics.framesizeLabelY, metrics.labelWidth, metrics.labelHeight);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Size of the program window");
    }

}
