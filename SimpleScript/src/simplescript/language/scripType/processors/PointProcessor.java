package simplescript.language.scripType.processors;

import java.awt.Color;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Point;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.program.gui.CanvasActions;

public class PointProcessor extends CommandProcessor {

    public PointProcessor(String commandStatement, CanvasActions canvas)
	    throws UnknownCommandException {
	super(commandStatement, canvas);
    }

    public Command buildExecutableCommand() {
	Color pointColor = Color.decode(commandParts[1]);
	int pointX = Integer.parseInt(commandParts[2]);
	int pointY = Integer.parseInt(commandParts[3]);

	return new Point(pointColor, pointX, pointY, processorCanvas);
    }
}
