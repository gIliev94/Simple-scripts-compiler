package simplescript.program.gui.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import simplescript.program.gui.backbone.OutputArea;

/**
 * Action listener for "EXIT" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ExitButtonListener extends AbstractButtonListener {

    JFrame frame;

    public ExitButtonListener(OutputArea area, JFrame frame) {
	super(area);
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	frame.dispose();
    }

}
