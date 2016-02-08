package simplescript.program.gui;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;
import simplescript.configurator.PrerequisitesConfigurator;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.program.gui.backbone.Canvas;
import simplescript.program.gui.backbone.FileChooser;
import simplescript.program.gui.backbone.Frame;
import simplescript.program.gui.backbone.OutputArea;
import simplescript.program.gui.backbone.TxtFileFilter;
import simplescript.program.gui.buttons.AbstractButton;
import simplescript.program.gui.buttons.ClearButton;
import simplescript.program.gui.buttons.CompileButton;
import simplescript.program.gui.buttons.DeleteButton;
import simplescript.program.gui.buttons.ExitButton;
import simplescript.program.gui.buttons.OpenButton;
import simplescript.program.gui.labels.AbstractLabel;
import simplescript.program.gui.labels.FrameSizeLabel;
import simplescript.program.gui.labels.ResolutionLabel;
import simplescript.program.gui.labels.TitleLabel;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.gui.listeners.ClearButtonListener;
import simplescript.program.gui.listeners.CompileButtonListener;
import simplescript.program.gui.listeners.DeleteButtonListener;
import simplescript.program.gui.listeners.ExitButtonListener;
import simplescript.program.gui.listeners.OpenButtonListener;
import simplescript.program.utilities.Display;
import simplescript.program.utilities.FrameMetrics;
import simplescript.program.utilities.RobotDelays;
import simplescript.program.utilities.StringConstants;

/**
 * Main class of the application.
 * 
 * @author Georgi Iliev
 *
 */
public class SimpleScriptMain {

    public static final Logger LOG = Logger.getLogger(SimpleScriptMain.class);

    JFrame frame;

    /**
     * Launch the application.
     * 
     */
    public static void main(String[] args) {
	try {
	    Thread.sleep(RobotDelays.START_PROGRAM_DELAY);

	    PrerequisitesConfigurator.configurePrerequisites();

	    Runnable mainExecutorThread = new Thread(new MainExecutorRunnable(), "main-executor-thread");

	    EventQueue.invokeAndWait(mainExecutorThread);

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null,
		    StringConstants.NEWLINE + "Unexpected error: " + e.getLocalizedMessage(), "ERROR",
		    JOptionPane.ERROR_MESSAGE);
	    LOG.error("Unexpected error: " + e.getLocalizedMessage(), e);
	}
    }

    /**
     * Create the application.
     * 
     * @throws AWTException
     * @throws InterruptedException
     * @throws IOException
     * @throws UnknownCommandException
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public SimpleScriptMain() throws AWTException, InterruptedException, IOException, UnknownCommandException,
	    ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	setComponentStyles();
	initialize();
    }

    /**
     * <h1><i>setComponentStyles</i></h1>
     * <p>
     * <p>
     * {@code private void setComponentStyles()}
     * </p>
     * Sets the look and feel styles of all visual components to Nimbus style or
     * Windows classic if the former is not installed. </p>
     * 
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    private void setComponentStyles() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
	    UnsupportedLookAndFeelException {

	boolean found = false;
	UIManager.LookAndFeelInfo iLAFs[] = UIManager.getInstalledLookAndFeels();

	for (int i = 0; i < iLAFs.length; i++) {
	    if (iLAFs[i].getName().equals("Nimbus")) {
		UIManager.setLookAndFeel(iLAFs[i].getClassName());
		found = true;
	    }
	}

	if (!found) {
	    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
	}
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws AWTException
     * @throws InterruptedException
     * @throws IOException
     * @throws UnknownCommandException
     */
    private void initialize() throws AWTException, InterruptedException, IOException, UnknownCommandException {

	Display display = new Display(Toolkit.getDefaultToolkit().getScreenSize());
	FrameMetrics frameMetrics = new FrameMetrics(display.width, display.height);

	final Canvas canvasPanel = new Canvas(frameMetrics.width, frameMetrics.height);

	String resolutionLabelText = "Resolution: " + display.getResolution();
	final AbstractLabel resolutionLabel = new ResolutionLabel(resolutionLabelText, frameMetrics);
	canvasPanel.add(resolutionLabel);

	String frameSizeLabeText = "Window Size: " + frameMetrics.getFrameSize();
	final AbstractLabel frameSizeLabel = new FrameSizeLabel(frameSizeLabeText, frameMetrics);
	canvasPanel.add(frameSizeLabel);

	Image icon = new ImageIcon(this.getClass().getResource("/simpleScriptLogo.png")).getImage();
	frame = new Frame(icon, frameMetrics);
	frame.getContentPane().add(canvasPanel);

	final JTextArea outputArea = new OutputArea(frameMetrics, canvasPanel);

	final AbstractButtonListener compileListener = new CompileButtonListener(outputArea, canvasPanel);
	final AbstractButton btnCompile = new CompileButton("RUN", frameMetrics, compileListener);
	canvasPanel.add(btnCompile);

	final AbstractButtonListener clearListener = new ClearButtonListener(outputArea, canvasPanel);
	final AbstractButton btnClear = new ClearButton("CLEAR", frameMetrics, clearListener);
	canvasPanel.add(btnClear);

	final AbstractButtonListener deleteListener = new DeleteButtonListener(outputArea);
	final AbstractButton btnDelete = new DeleteButton("DELETE", frameMetrics, deleteListener);
	canvasPanel.add(btnDelete);

	final AbstractButtonListener exitListener = new ExitButtonListener(outputArea, frame);
	final AbstractButton btnExit = new ExitButton("EXIT", frameMetrics, exitListener);
	canvasPanel.add(btnExit);

	final JFileChooser fileChooser = new FileChooser(new TxtFileFilter(), frameMetrics);
	final AbstractButtonListener openListener = new OpenButtonListener(outputArea, fileChooser, compileListener);
	final AbstractButton btnOpen = new OpenButton("OPEN", frameMetrics, openListener);
	canvasPanel.add(btnOpen);

	final AbstractLabel titleLabel = new TitleLabel("simpleScript", frameMetrics, canvasPanel);
	canvasPanel.add(titleLabel);
    }

}
