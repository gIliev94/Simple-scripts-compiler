package simplescript.language.scripType.commands;

import java.awt.Color;

import simplescript.program.gui.CanvasActions;

public class Point extends Command {

    protected CanvasActions pointCanvas;
    protected Color pointColor;
    protected int x;
    protected int y;

    public Point() {
    }

    public Point(Color color, int x, int y, CanvasActions canvas) {
	this.pointCanvas = canvas;
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

    public String getCommandFormat() {
	return "POINT\\s#[0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF][0123456789ABCDEF]\\s[0-9][0-9][0-9]\\s[0-9][0-9][0-9]";
    }
}
