package simplescript.language.scripType.commands;

import java.awt.Color;

import simplescript.program.gui.Canvas;

public class Point extends Command {

    protected Canvas pointCanvas;
    protected Color pointColor;
    protected int x;
    protected int y;

    public Point() {
    }

    public Point(Color color, int x, int y, Canvas processorCanvas) {
	this.pointCanvas = processorCanvas;
	this.pointColor = color;
	this.x = x;
	this.y = y;
    }

    public void execute() {
	pointCanvas.drawPoint(pointColor, x, y);
    }

    public String toString() {
	return "point";
    }
}
