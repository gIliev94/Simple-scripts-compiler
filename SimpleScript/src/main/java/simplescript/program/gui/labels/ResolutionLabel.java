package simplescript.program.gui.labels;

import java.util.UUID;

import simplescript.program.utilities.ComponentMetrics;

/**
 * JLabel implementation for resolution label.
 * 
 * @author Georgi Iliev
 *
 */
public class ResolutionLabel extends AbstractLabel {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public ResolutionLabel(String labelText, ComponentMetrics metrics) {
	super(labelText, metrics);
	this.setBounds(metrics.resolutionLabelX, metrics.resolutionLabelY, metrics.labelWidth, metrics.labelHeight);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setToolTipText("Current display resolution");
    }

}
