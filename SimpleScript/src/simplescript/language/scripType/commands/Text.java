package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import simplescript.program.utilities.RobotDelays;

public class Text extends Command {

    protected String text;

    public Text() {
    }

    public Text(String textToType) {
	this.text = textToType;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.TYPING_DELAY);

	char[] keys = text.toCharArray();

	for (int i = 0; i < keys.length; i++) {
	    if (Character.isWhitespace(keys[i])) {
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
	    } else if (Character.isUpperCase(keys[i])) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(Character.toUpperCase(keys[i]));

		robot.keyRelease(Character.toUpperCase(keys[i]));
		robot.keyRelease(KeyEvent.VK_SHIFT);
	    } else if (keys[i] == ',') {
		robot.keyPress(KeyEvent.VK_COMMA);
		robot.keyRelease(KeyEvent.VK_COMMA);
	    } else {
		robot.keyPress(Character.toUpperCase(keys[i]));
		robot.keyRelease(Character.toUpperCase(keys[i]));
	    }
	}
    }

    public String toString() {
	return "text";
    }
}
