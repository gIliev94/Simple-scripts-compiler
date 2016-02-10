package simplescript.program.utilities;

/**
 * Utility class - alternative to LAYOUT, sets up relative positions of
 * components using offsets by predefined percentages.
 * 
 * @author Georgi Iliev
 * 
 */
public class ComponentMetrics {

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
    public final int runButtonX;
    public final int runButtonY;
    public final int clearButtonX;
    public final int clearButtonY;
    public final int deleteButtonX;
    public final int deleteButtonY;
    public final int exitButtonX;
    public final int exitButtonY;
    public final int openButtonX;
    public final int openButtonY;
    public final int textAreaX;
    public final int textAreaY;
    public final int textAreaWidth;
    public final int textAreaHeight;

    public ComponentMetrics(int displayWidth, int displayHeight) {
	this.x = displayWidth / 6;
	this.y = displayHeight / 5;
	this.width = displayWidth / 3;
	this.height = displayWidth / 3;

	this.labelWidth = scaleToPercentage(66);
	this.labelHeight = scaleToPercentage(95);

	this.resolutionLabelX = scaleToPercentage(65);
	this.resolutionLabelY = scaleToPercentage(16);

	this.framesizeLabelX = scaleToPercentage(65);
	this.framesizeLabelY = scaleToPercentage(12);

	this.captionLabelX = scaleToPercentage(72);
	this.captionLabelY = scaleToPercentage(99);
	this.captionLabelWidth = scaleToPercentage(61);
	this.captionLabelHeight = scaleToPercentage(97);

	this.buttonWidth = scaleToPercentage(80);
	this.buttonHeight = scaleToPercentage(95);

	this.openButtonX = scaleToPercentage(88);
	this.openButtonY = scaleToPercentage(95);

	this.runButtonX = scaleToPercentage(62);
	this.runButtonY = scaleToPercentage(95);

	this.clearButtonX = scaleToPercentage(36);
	this.clearButtonY = scaleToPercentage(95);

	this.deleteButtonX = scaleToPercentage(95);
	this.deleteButtonY = scaleToPercentage(15);

	this.exitButtonX = scaleToPercentage(25);
	this.exitButtonY = scaleToPercentage(15);

	this.textAreaX = scaleToPercentage(88);
	this.textAreaY = scaleToPercentage(88);
	this.textAreaWidth = scaleToPercentage(28);
	this.textAreaHeight = scaleToPercentage(85);
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

    private int scaleToPercentage(int percentage) {
	if (percentage < 1 || percentage > 100) {
	    throw new RuntimeException("Invalid scaling data: Failed to scale component!");
	}

	return width - ((percentage * width) / 100);
    }
}
