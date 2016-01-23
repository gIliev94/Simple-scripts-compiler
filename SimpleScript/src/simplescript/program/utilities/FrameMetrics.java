package simplescript.program.utilities;

/**
 * <h1>Utility class - alternative to LAYOUT, sets up relative positions of
 * elements</h1>
 * <p>
 * </p>
 * 
 * @author Georgi Iliev
 */
public class FrameMetrics {

    public final int X;
    public final int Y;
    public final int WIDTH;
    public final int HEIGHT;
    public final int LABELS_WIDTH;
    public final int LABELS_HEIGHT;
    public final int RESOLUTION_LABEL_X;
    public final int RESOLUTION_LABEL_Y;
    public final int FRAMESIZE_LABEL_X;
    public final int FRAMESIZE_LABEL_Y;
    public final int CAPTION_LABEL_X;
    public final int CAPTION_LABEL_Y;
    public final int CAPTION_LABEL_WIDTH;
    public final int CAPTION_LABEL_HEIGHT;
    public final int BUTTONS_WIDTH;
    public final int BUTTONS_HEIGHT;
    public final int COMPILE_BUTTON_X;
    public final int COMPILE_BUTTON_Y;
    public final int CLEAR_BUTTON_X;
    public final int CLEAR_BUTTON_Y;
    public final int DELETE_BUTTON_X;
    public final int DELETE_BUTTON_Y;
    public final int BACK_BUTTON_X;
    public final int BACK_BUTTON_Y;
    public final int OPEN_BUTTON_X;
    public final int OPEN_BUTTON_Y;
    public final int TEXT_AREA_X;
    public final int TEXT_AREA_Y;
    public final int TEXT_AREA_WIDTH;
    public final int TEXT_AREA_HEIGHT;

    public FrameMetrics(int displayWidth, int displayHeight) {
	this.X = displayWidth / 6;
	this.Y = displayHeight / 5;
	this.WIDTH = displayWidth / 3;
	this.HEIGHT = displayWidth / 3;

	this.LABELS_WIDTH = WIDTH / 3 + 20;
	this.LABELS_HEIGHT = WIDTH - ((95 * WIDTH) / 100);

	this.RESOLUTION_LABEL_X = WIDTH / 3 + 15;
	this.RESOLUTION_LABEL_Y = WIDTH - ((16 * WIDTH) / 100);

	this.FRAMESIZE_LABEL_X = WIDTH / 3 + 15;
	this.FRAMESIZE_LABEL_Y = WIDTH - ((12 * WIDTH) / 100);

	this.CAPTION_LABEL_X = WIDTH - ((72 * WIDTH) / 100);
	this.CAPTION_LABEL_Y = WIDTH - ((99 * WIDTH) / 100);
	this.CAPTION_LABEL_WIDTH = WIDTH - ((61 * WIDTH) / 100);
	this.CAPTION_LABEL_HEIGHT = WIDTH - ((97 * WIDTH) / 100);

	this.BUTTONS_WIDTH = WIDTH - ((82 * WIDTH) / 100) + 20;
	this.BUTTONS_HEIGHT = WIDTH - ((95 * WIDTH) / 100);

	this.COMPILE_BUTTON_X = WIDTH - ((65 * WIDTH) / 100) + 14;
	this.COMPILE_BUTTON_Y = WIDTH - ((95 * WIDTH) / 100);

	this.CLEAR_BUTTON_X = WIDTH - ((39 * WIDTH) / 100) + 10;
	this.CLEAR_BUTTON_Y = WIDTH - ((95 * WIDTH) / 100);

	this.DELETE_BUTTON_X = WIDTH - ((92 * WIDTH) / 100) - 20;
	this.DELETE_BUTTON_Y = WIDTH - ((15 * WIDTH) / 100);

	this.BACK_BUTTON_X = WIDTH - ((25 * WIDTH) / 100) - 10;
	this.BACK_BUTTON_Y = WIDTH - ((15 * WIDTH) / 100);

	this.OPEN_BUTTON_X = WIDTH - ((90 * WIDTH) / 100) + 10;
	this.OPEN_BUTTON_Y = WIDTH - ((95 * WIDTH) / 100);

	this.TEXT_AREA_X = WIDTH - ((88 * WIDTH) / 100) + 8;
	this.TEXT_AREA_Y = WIDTH - ((88 * WIDTH) / 100);
	this.TEXT_AREA_WIDTH = WIDTH - ((30 * WIDTH) / 100);
	this.TEXT_AREA_HEIGHT = WIDTH - ((85 * WIDTH) / 100);
    }

    /**
     * <h1><i>getFrameSize</i></h1>
     * <p>
     * <p>
     * {@code public String getFrameSize()}
     * </p>
     * Formats a string representing the resolution(size) of the frame. </p>
     * 
     * @return A string representing the resolution(size) of the frame.
     */
    public String getFrameSize() {
	return WIDTH + "x" + HEIGHT;
    }
}
