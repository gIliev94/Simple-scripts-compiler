package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

/**
 * Implementation class for the scripType command - "DELAY".
 * 
 * @author Georgi Iliev
 *
 */
public class Delay extends Command {

    protected String delay;

    public Delay(String delayToExecute) {
	this.delay = delayToExecute;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(Integer.parseInt(delay));
    }

    @Override
    public String toString() {
	return "delay";
    }

}
