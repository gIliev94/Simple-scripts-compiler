package simplescript.program.utilities;

import java.awt.Dimension;

/**
 * <h1>Utility class - describes the current display metrics</h1>
 * <p>
 * </p>
 * 
 * @since 2015-11-15
 * @author Georgi Iliev
 * @version 1.0
 */
public class Display {

    public final int WIDTH;
    public final int HEIGHT;

    public Display(Dimension dimension) {
	this.WIDTH = dimension.width;
	this.HEIGHT = dimension.height;
    }

    public String getResolution() {
	return WIDTH + "x" + HEIGHT;
    }
}
