package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class Delay extends Command {

    protected String delay;

    public Delay() {
    }

    public Delay(String delayToExecute) {
	this.delay = delayToExecute;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(Integer.parseInt(delay));
    }

    public String toString() {
	return "delay";
    }

    public String getCommandFormat() {
	return "^DELAY\\s[0-9]+$";
    }
}
