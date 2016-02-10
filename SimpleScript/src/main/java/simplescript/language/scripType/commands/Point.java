package simplescript.language.scripType.commands;

import java.awt.Color;
import simplescript.program.gui.backbone.Canvas;

/**
 * Implementation class for the scripType command - "POINT".
 * 
 * @author Georgi Iliev
 *
 */
public class Point extends Command {

    protected Canvas canvas;
    protected Color pointColor;
    protected int x;
    protected int y;

    public Point(Color pointColor, int x, int y, Canvas canvasPanel) {
	this.canvas = canvasPanel;
	this.pointColor = pointColor;
	this.x = x;
	this.y = y;
    }

    public void execute() {
	canvas.drawPoint(pointColor, x, y);
    }

    @Override
    public String toString() {
	return "point";
    }

}
