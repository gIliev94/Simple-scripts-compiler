package simplescript.program.gui.labels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.util.UUID;
import javax.swing.SwingConstants;
import simplescript.program.gui.backbone.Canvas;
import simplescript.program.utilities.FrameMetrics;

/**
 * JLabel implementation of title label.
 * 
 * @author Georgi Iliev
 *
 */
public class TitleLabel extends AbstractLabel {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public TitleLabel(String labelText, FrameMetrics metrics, Canvas canvasPanel) {
	super(labelText, metrics);
	this.setBounds(metrics.captionLabelX, metrics.captionLabelY, metrics.captionLabelWidth,
		metrics.captionLabelHeight);
	this.setFont(new Font("Ravie", Font.PLAIN, 18));
	this.setLabelFor(canvasPanel);
	setAdditionalAttributes();
    }

    @Override
    public void setAdditionalAttributes() {
	this.setFocusable(false);
	this.setIgnoreRepaint(true);
	this.setHorizontalTextPosition(SwingConstants.CENTER);
	this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	this.setAlignmentX(Component.CENTER_ALIGNMENT);
	this.setHorizontalAlignment(SwingConstants.CENTER);
	this.setForeground(Color.YELLOW);
    }

}
