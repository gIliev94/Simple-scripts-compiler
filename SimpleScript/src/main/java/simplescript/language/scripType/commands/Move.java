package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import simplescript.program.utilities.RobotDelays;

/**
 * Implementation class for the scripType command - "MOVE".
 * 
 * @author Georgi Iliev
 *
 */
public class Move extends Command {

    protected String targetX;
    protected String targetY;

    public Move(String targetX, String targetY) {
	this.targetX = targetX;
	this.targetY = targetY;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.VIEWING_DELAY);

	int x = Integer.parseInt(targetX);
	int y = Integer.parseInt(targetY);

	robot.mouseMove(x, y);
    }

    @Override
    public String toString() {
	return "move";
    }

}
