package simplescript.program.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import simplescript.program.gui.backbone.OutputArea;

/**
 * Abstract model of a button action listener in SimpleScript.
 * 
 * @author Georgi Iliev
 *
 */
public abstract class AbstractButtonListener implements ActionListener {

    protected OutputArea output;

    public AbstractButtonListener(OutputArea outputArea) {
	this.output = outputArea;
    }

    public abstract void actionPerformed(ActionEvent e);

}
