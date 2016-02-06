package simplescript.language.scripType.commands;

import java.awt.Color;
import java.awt.Point;
import simplescript.program.gui.backbone.Canvas;

/**
 * Implementation class for the scripType command - "LINE".
 * 
 * @author Georgi Iliev
 *
 */
public class Line extends Command {

    protected Canvas lineCanvas;
    protected Color lineColor;
    protected Point startPoint;
    protected Point endPoint;
    protected boolean lineType;

    public Line(Color color, Point start, Point end, boolean type, Canvas processorCanvas) {
	this.lineCanvas = processorCanvas;
	this.lineColor = color;
	this.startPoint = start;
	this.endPoint = end;
	this.lineType = type;
    }

    public void execute() {
	lineCanvas.drawLine(lineColor, startPoint.x, startPoint.y, endPoint.x, endPoint.y, lineType);
    }

    @Override
    public String toString() {
	return "line";
    }

}
