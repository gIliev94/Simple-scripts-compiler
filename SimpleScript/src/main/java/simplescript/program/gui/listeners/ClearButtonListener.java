package simplescript.program.gui.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;

import simplescript.program.gui.backbone.Canvas;
import simplescript.program.gui.backbone.OutputArea;

/**
 * Action listener for "CLEAR" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ClearButtonListener extends AbstractButtonListener {

    Canvas canvasPanel;

    public ClearButtonListener(OutputArea area, Canvas canvasPanel) {
	super(area);
	this.canvasPanel = canvasPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	canvasPanel.fillCanvas(Color.BLACK);
    }

}
