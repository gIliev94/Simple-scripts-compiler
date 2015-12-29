package simplescript.program.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * <h1>Environment class - sets up the canvas for drawing purposes</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-23
 * @author Georgi Iliev
 * @version 1.7
 */
public class CanvasActions extends JPanel {

    private static final long serialVersionUID = 1L;

    private BufferedImage canvas;
    private BasicStroke dashPattern;
    private Graphics2D dashedLineDrawer;
    private Graphics2D continuousLineDrawer;

    public CanvasActions(int width, int height) throws InterruptedException {
	canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	dashedLineDrawer = canvas.createGraphics();
	dashPattern = createDashStroke();
	continuousLineDrawer = canvas.createGraphics();
	fillCanvas(Color.BLACK);
    }

    /**
     * 
     * <h1><i>fillCanvas</i></h1>
     * <p>
     * <p>
     * {@code private void fillCanvas(Color color)}
     * </p>
     * Paints the whole canvas surface with the given color. </p>
     * 
     * @param color
     *            - the color to fill the whole canvas with.
     */
    public void fillCanvas(Color color) {
	int colorCode = color.getRGB();
	for (int x = 0; x < canvas.getWidth(); x++) {
	    for (int y = 0; y < canvas.getHeight(); y++) {
		canvas.setRGB(x, y, colorCode);
	    }
	}
	repaint();
    }

    /**
     * <h1><i>createDashStroke</i></h1>
     * <p>
     * <p>
     * {@code private BasicStroke createDashStroke()}
     * </p>
     * Sets up the dash parameters, formats and returns a stroke style. </p>
     * 
     * @return BasicStroke object that represents a stroke style.
     */
    private BasicStroke createDashStroke() {
	float[] dash = new float[] { 4.0f, 4.0f };
	BasicStroke dashStroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
		BasicStroke.JOIN_BEVEL, 0.0f, dash, 0);
	return dashStroke;
    }

    /**
     * <h1><i>drawLine</i></h1>
     * <p>
     * <p>
     * {@code public void drawLine(Color color, int x1, int y1, int x2, int y2,
	    boolean type)}
     * </p>
     * Draws a line based on the given parameters. </p>
     * 
     * @param color
     *            - the color of the line in hexadecimal format.
     * @param x1
     *            - horizontal coordinate of starting point.
     * @param y1
     *            - vertical coordinate of starting point.
     * @param x2
     *            - horizontal coordinate of end point.
     * @param y2
     *            - vertical coordinate of end point.
     * @param type
     *            - type of the line( dashed/continuous )
     */
    public void drawLine(Color color, int x1, int y1, int x2, int y2,
	    boolean type) {
	dashedLineDrawer.setColor(color);
	continuousLineDrawer.setColor(color);

	if (type) {
	    continuousLineDrawer.drawLine(x1, y1, x2, y2);
	} else {
	    dashedLineDrawer.setStroke(dashPattern);
	    dashedLineDrawer.drawLine(x1, y1, x2, y2);
	}

	repaint();
    }

    /**
     * <h1><i>drawPoint</i></h1>
     * <p>
     * <p>
     * {@code public void drawPoint(Color color, int x, int y)}
     * </p>
     * Draws a point based on the given parameters. </p>
     * 
     * @param color
     *            - the color of the point in hexadecimal format.
     * @param x
     *            - horizontal coordinate of point.
     * @param y
     *            - vertical coordinate of point.
     */
    public void drawPoint(Color color, int x, int y) {
	int colorCode = color.getRGB();
	canvas.setRGB(x, y, colorCode);
	repaint();
    }

    public Dimension getPreferredSize() {
	return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
}
