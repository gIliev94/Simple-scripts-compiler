package simplescript.language.scripType.processors;

import java.awt.Color;
import java.awt.Point;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.Line;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.program.gui.backbone.Canvas;

/**
 * Implementation of a processor for the scripType command - "LINE".
 * 
 * @author Georgi Iliev
 *
 */
public class LineProcessor extends CommandProcessor {

    public LineProcessor(String commandStatement, Canvas canvasPanel) throws UnknownCommandException {
	super(commandStatement, canvasPanel);
    }

    public Command buildExecutableCommand() throws NumberFormatException {
	Color lineColor = Color.decode(commandParts[1]);
	Point startPoint = new Point(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[3]));
	Point endPoint = new Point(Integer.parseInt(commandParts[4]), Integer.parseInt(commandParts[5]));
	boolean type = commandParts[6].equals("1");

	return new Line(lineColor, startPoint, endPoint, type, canvas);
    }

}
