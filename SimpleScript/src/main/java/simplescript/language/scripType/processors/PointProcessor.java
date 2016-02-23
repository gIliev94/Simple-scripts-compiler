package simplescript.language.scripType.processors;

import java.awt.Color;

import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Point;
import simplescript.program.gui.backbone.Canvas;

/**
 * Implementation of a processor for scripType command - "POINT".
 * 
 * @author Georgi Iliev
 *
 */
public class PointProcessor extends CommandProcessor {

    public PointProcessor(String commandStatement, Canvas canvasPanel) {
	super(commandStatement, canvasPanel);
    }

    public Command buildExecutableCommand() {
	Color pointColor = Color.decode(commandParts[1]);
	int pointX = Integer.parseInt(commandParts[2]);
	int pointY = Integer.parseInt(commandParts[3]);

	return new Point(pointColor, pointX, pointY, canvas);
    }

}
