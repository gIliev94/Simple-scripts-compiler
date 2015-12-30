package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import simplescript.program.utilities.RobotDelays;

public class Move extends Command {

    protected String mouseMoveX;
    protected String mouseMoveY;

    public Move() {
    }

    public Move(String moveX, String moveY) {
	this.mouseMoveX = moveX;
	this.mouseMoveY = moveY;
    }

    public void execute() throws AWTException, IOException {
	Robot robot = new Robot();

	robot.delay(RobotDelays.INITIAL_DELAY);
	robot.setAutoDelay(RobotDelays.VIEWING_DELAY);

	int x = Integer.parseInt(mouseMoveX);
	int y = Integer.parseInt(mouseMoveY);

	robot.mouseMove(x, y);
    }

    public String toString() {
	return "click";
    }

    public String getCommandFormat() {
	return "^MOVE\\s[0-9]+\\s[0-9]+$";
    }
}
