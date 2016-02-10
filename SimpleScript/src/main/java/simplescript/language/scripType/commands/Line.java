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

    protected Canvas canvas;
    protected Color lineColor;
    protected Point startPoint;
    protected Point endPoint;
    protected boolean lineType;

    public Line(Color lineColor, Point startPoint, Point endPoint, boolean lineType, Canvas canvasPanel) {
	this.canvas = canvasPanel;
	this.lineColor = lineColor;
	this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.lineType = lineType;
    }

    public void execute() {
	canvas.drawLine(lineColor, startPoint.x, startPoint.y, endPoint.x, endPoint.y, lineType);
    }

    @Override
    public String toString() {
	return "line";
    }

}
