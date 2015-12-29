package simplescript.language.scripType.commands;

import java.awt.Color;
import java.awt.Point;

import simplescript.program.gui.CanvasActions;

public class Line extends Command {

    protected CanvasActions lineCanvas;
    protected Color lineColor;
    protected Point startPoint;
    protected Point endPoint;
    protected boolean lineType;

    public Line() {
    }

    public Line(Color color, Point start, Point end, boolean type,
	    CanvasActions canvas) {
	this.lineCanvas = canvas;
	this.lineColor = color;
	this.startPoint = start;
	this.endPoint = end;
	this.lineType = type;
    }

    public void execute() {
	lineCanvas.drawLine(lineColor, startPoint.x, startPoint.y, endPoint.x,
		endPoint.y, lineType);
    }

    public String toString() {
	return "line";
    }

    public String getCommandFormat() {
	return "LINE\\s#[0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]\\s[0-1]";
    }
}
