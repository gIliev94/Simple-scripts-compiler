package simplescript.program.gui.backbone;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.util.UUID;
import javax.swing.JFrame;
import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.utilities.ComponentMetrics;

/**
 * JFrame implementation for main UI look of SimpleScript�.
 * 
 * @author Georgi Iliev
 *
 */
public class Frame extends JFrame implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public Frame(Image icon, ComponentMetrics metrics) {
	this.setBounds(metrics.x, metrics.y, metrics.width, metrics.height);
	this.setIconImage(icon);
	setAdditionalAttributes();
    }

    public void setAdditionalAttributes() {
	this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	this.setTitle("SimpleScript\u2122");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setLayout(new CardLayout(0, 0));
	this.setResizable(false);
    }

}
