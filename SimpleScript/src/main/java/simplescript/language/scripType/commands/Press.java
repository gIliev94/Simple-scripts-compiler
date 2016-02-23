package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import simplescript.program.utilities.RobotDelays;

/**
 * Implementation class for the scripType command - "PRESS".
 * 
 * @author Georgi Iliev
 *
 */
public class Press extends Command {

    protected String shortcut;

    public Press(String shortcut) {
	this.shortcut = shortcut;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.VIEWING_DELAY);

	if (shortcut.equalsIgnoreCase("alt+f4")) {
	    robot.keyPress(KeyEvent.VK_ALT);
	    robot.keyPress(KeyEvent.VK_F4);

	    robot.keyRelease(KeyEvent.VK_F4);
	    robot.keyRelease(KeyEvent.VK_ALT);
	} else if (shortcut.equalsIgnoreCase("ctrl+s")) {
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);

	    robot.keyRelease(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	} else if (shortcut.equalsIgnoreCase("ctrl+a")) {
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_A);

	    robot.keyRelease(KeyEvent.VK_A);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	} else if (shortcut.equalsIgnoreCase("del")) {
	    robot.keyPress(KeyEvent.VK_DELETE);

	    robot.keyRelease(KeyEvent.VK_DELETE);
	} else if (shortcut.equalsIgnoreCase("w+t")) {
	    robot.keyPress(KeyEvent.VK_W);
	    robot.keyRelease(KeyEvent.VK_W);

	    robot.keyPress(KeyEvent.VK_T);
	    robot.keyRelease(KeyEvent.VK_T);
	} else if (shortcut.equalsIgnoreCase("enter")) {
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	} else if (shortcut.equalsIgnoreCase("f5")) {
	    robot.keyPress(KeyEvent.VK_F5);
	    robot.keyRelease(KeyEvent.VK_F5);
	} else if (shortcut.equalsIgnoreCase("f2")) {
	    robot.keyPress(KeyEvent.VK_F2);
	    robot.keyRelease(KeyEvent.VK_F2);
	}
    }

    @Override
    public String toString() {
	return "press";
    }

}
