package simplescript.program.gui.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import simplescript.program.gui.backbone.Frame;

/**
 * Action listener for "EXIT" button.
 * 
 * @author Georgi Iliev
 *
 */
public class ExitButtonListener extends AbstractButtonListener {

    Frame frame;

    public ExitButtonListener(JTextArea area, Frame frame) {
	super(area);
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	frame.dispose();
    }

}
