package simplescript.program.utilities;

import java.awt.Dimension;

/**
 * Utility class - holds the current display metrics.
 * 
 * @author Georgi Iliev
 * 
 */
public class Display {

    public final int width;
    public final int height;

    public Display(Dimension dimension) {
	this.width = dimension.width;
	this.height = dimension.height;
    }

    public String getResolution() {
	return width + "x" + height;
    }
}
