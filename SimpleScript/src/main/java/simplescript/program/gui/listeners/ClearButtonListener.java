package simplescript.program.gui.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import simplescript.program.gui.backbone.Canvas;

/**
 * Action listener for "CLEAR" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ClearButtonListener extends AbstractButtonListener {

    Canvas canvasPanel;

    public ClearButtonListener(JTextArea area, Canvas canvasPanel) {
	super(area);
	this.canvasPanel = canvasPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	canvasPanel.fillCanvas(Color.BLACK);
    }

}
