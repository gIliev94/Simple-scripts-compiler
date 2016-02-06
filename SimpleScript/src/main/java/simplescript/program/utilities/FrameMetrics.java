package simplescript.program.utilities;

/**
 * Utility class - alternative to LAYOUT, sets up relative positions of
 * elements.
 * 
 * @author Georgi Iliev
 * 
 */
public class FrameMetrics {

    public final int x;
    public final int y;
    public final int width;
    public final int height;
    public final int labelWidth;
    public final int labelHeight;
    public final int resolutionLabelX;
    public final int resolutionLabelY;
    public final int framesizeLabelX;
    public final int framesizeLabelY;
    public final int captionLabelX;
    public final int captionLabelY;
    public final int captionLabelWidth;
    public final int captionLabelHeight;
    public final int buttonWidth;
    public final int buttonHeight;
    public final int compileButtonX;
    public final int compileButtonY;
    public final int clearButtonX;
    public final int clearButtonY;
    public final int deleteButtonX;
    public final int deleteButtonY;
    public final int backButtonX;
    public final int backButtonY;
    public final int openButtonX;
    public final int openButtonY;
    public final int textAreaX;
    public final int textAreaY;
    public final int textAreaWidth;
    public final int textAreaHeight;

    public FrameMetrics(int displayWidth, int displayHeight) {
	this.x = displayWidth / 6;
	this.y = displayHeight / 5;
	this.width = displayWidth / 3;
	this.height = displayWidth / 3;

	this.labelWidth = width / 3 + 20;
	this.labelHeight = width - ((95 * width) / 100);

	this.resolutionLabelX = width / 3 + 15;
	this.resolutionLabelY = width - ((16 * width) / 100);

	this.framesizeLabelX = width / 3 + 15;
	this.framesizeLabelY = width - ((12 * width) / 100);

	this.captionLabelX = width - ((72 * width) / 100);
	this.captionLabelY = width - ((99 * width) / 100);
	this.captionLabelWidth = width - ((61 * width) / 100);
	this.captionLabelHeight = width - ((97 * width) / 100);

	this.buttonWidth = width - ((82 * width) / 100) + 20;
	this.buttonHeight = width - ((95 * width) / 100);

	this.compileButtonX = width - ((65 * width) / 100) + 14;
	this.compileButtonY = width - ((95 * width) / 100);

	this.clearButtonX = width - ((39 * width) / 100) + 10;
	this.clearButtonY = width - ((95 * width) / 100);

	this.deleteButtonX = width - ((92 * width) / 100) - 20;
	this.deleteButtonY = width - ((15 * width) / 100);

	this.backButtonX = width - ((25 * width) / 100) - 10;
	this.backButtonY = width - ((15 * width) / 100);

	this.openButtonX = width - ((90 * width) / 100) + 10;
	this.openButtonY = width - ((95 * width) / 100);

	this.textAreaX = width - ((88 * width) / 100) + 8;
	this.textAreaY = width - ((88 * width) / 100);
	this.textAreaWidth = width - ((30 * width) / 100);
	this.textAreaHeight = width - ((85 * width) / 100);
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
	return width + "x" + height;
    }
}
