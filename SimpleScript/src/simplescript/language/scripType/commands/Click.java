package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

import simplescript.program.utilities.RobotDelays;

public class Click extends Command {

    protected String mouseButton;

    public Click() {
    }

    public Click(String clickToPerform) {
	this.mouseButton = clickToPerform;
    }

    public void execute() throws AWTException, IOException,
	    IllegalArgumentException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.VIEWING_DELAY);

	if (mouseButton.equalsIgnoreCase("right")) {
	    robot.mousePress(InputEvent.getMaskForButton(3));
	    robot.mouseRelease(InputEvent.getMaskForButton(3));
	    robot.mousePress(InputEvent.getMaskForButton(2));
	    robot.mouseRelease(InputEvent.getMaskForButton(2));

	} else if (mouseButton.equalsIgnoreCase("left")) {
	    robot.mousePress(InputEvent.getMaskForButton(1));
	    robot.mouseRelease(InputEvent.getMaskForButton(1));

	} else if (mouseButton.equalsIgnoreCase("leftdouble")) {
	    robot.setAutoDelay(RobotDelays.DOUBLECLICK_DELAY);
	    robot.mousePress(InputEvent.getMaskForButton(1));
	    robot.mouseRelease(InputEvent.getMaskForButton(1));
	    robot.mousePress(InputEvent.getMaskForButton(1));
	    robot.mouseRelease(InputEvent.getMaskForButton(1));

	}
    }

    public String toString() {
	return "click";
    }

    public String getCommandFormat() {
	return "CLICK\\sLEFT|RIGHT|LEFTDOUBLE";
    }
}
