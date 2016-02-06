package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import simplescript.program.utilities.RobotDelays;

/**
 * Implementation class for the scripType command - "CLICK".
 * 
 * @author Georgi Iliev
 *
 */
public class Click extends Command {

    protected String mouseButton;

    public Click(String clickToPerform) {
	this.mouseButton = clickToPerform;
    }

    public void execute() throws AWTException, IOException, IllegalArgumentException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.VIEWING_DELAY);

	int button = 0;

	if (mouseButton.equalsIgnoreCase("right")) {
	    button = InputEvent.getMaskForButton(3);
	    robot.mousePress(button);
	    robot.mouseRelease(button);

	    button = InputEvent.getMaskForButton(2);
	    robot.mousePress(button);
	    robot.mouseRelease(button);
	} else if (mouseButton.equalsIgnoreCase("left")) {
	    button = InputEvent.getMaskForButton(1);

	    robot.mousePress(button);
	    robot.mouseRelease(button);
	} else if (mouseButton.equalsIgnoreCase("leftdouble")) {
	    robot.setAutoDelay(RobotDelays.DOUBLECLICK_DELAY);
	    button = InputEvent.getMaskForButton(1);

	    robot.mousePress(button);
	    robot.mouseRelease(button);

	    robot.mousePress(button);
	    robot.mouseRelease(button);
	}
    }

    @Override
    public String toString() {
	return "click";
    }

}
