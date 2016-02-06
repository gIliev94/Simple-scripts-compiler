package simplescript.program.gui.backbone;

import java.util.UUID;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.utilities.FrameMetrics;

/**
 * JTextArea implementation for resulting output of script execution.
 * 
 * @author Georgi Iliev
 *
 */
public class OutputArea extends JTextArea implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public OutputArea(FrameMetrics metrics, Canvas canvasPanel) {
	this.setBounds(metrics.textAreaX, metrics.textAreaY, metrics.textAreaWidth, metrics.textAreaHeight);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(metrics.textAreaX, metrics.textAreaY, metrics.textAreaWidth, metrics.textAreaHeight);
	canvasPanel.add(scrollPane);
	scrollPane.setViewportView(this);
	setAdditionalAttributes();
    }

    public void setAdditionalAttributes() {
	this.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	this.setToolTipText("Shows status of current execution");
	this.setEditable(false);
	this.setFocusable(false);
    }

}
