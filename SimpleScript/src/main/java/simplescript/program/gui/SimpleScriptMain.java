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
import simplescript.program.gui.buttons.RunButton;
import simplescript.program.gui.buttons.DeleteButton;
import simplescript.program.gui.buttons.ExitButton;
import simplescript.program.gui.buttons.OpenButton;
import simplescript.program.gui.labels.AbstractLabel;
import simplescript.program.gui.labels.FrameSizeLabel;
import simplescript.program.gui.labels.ResolutionLabel;
import simplescript.program.gui.labels.TitleLabel;
import simplescript.program.gui.listeners.AbstractButtonListener;
import simplescript.program.gui.listeners.ClearButtonListener;
import simplescript.program.gui.listeners.RunButtonListener;
import simplescript.program.gui.listeners.DeleteButtonListener;
import simplescript.program.gui.listeners.ExitButtonListener;
import simplescript.program.gui.listeners.OpenButtonListener;
import simplescript.program.utilities.Display;
import simplescript.program.utilities.ComponentMetrics;
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
	ComponentMetrics metrics = new ComponentMetrics(display.width, display.height);

	final Canvas panelCanvas = new Canvas(metrics.width, metrics.height);

	String resolutionLabelText = "Resolution: " + display.getResolution();
	final AbstractLabel labelResolution = new ResolutionLabel(resolutionLabelText, metrics);
	panelCanvas.add(labelResolution);

	String frameSizeLabeText = "Window Size: " + metrics.getFrameSize();
	final AbstractLabel labelFrameSize = new FrameSizeLabel(frameSizeLabeText, metrics);
	panelCanvas.add(labelFrameSize);

	Image icon = new ImageIcon(this.getClass().getResource("/simpleScriptLogo.png")).getImage();
	frame = new Frame(icon, metrics);
	frame.getContentPane().add(panelCanvas);

	final JTextArea areaOutput = new OutputArea(metrics, panelCanvas);

	final AbstractButtonListener actionRun = new RunButtonListener(areaOutput, panelCanvas);
	final AbstractButton buttonRun = new RunButton("RUN", metrics, actionRun);
	panelCanvas.add(buttonRun);

	final AbstractButtonListener actionClear = new ClearButtonListener(areaOutput, panelCanvas);
	final AbstractButton buttonClear = new ClearButton("CLEAR", metrics, actionClear);
	panelCanvas.add(buttonClear);

	final AbstractButtonListener actionDelete = new DeleteButtonListener(areaOutput);
	final AbstractButton buttonDelete = new DeleteButton("DELETE", metrics, actionDelete);
	panelCanvas.add(buttonDelete);

	final AbstractButtonListener actionExit = new ExitButtonListener(areaOutput, frame);
	final AbstractButton buttonExit = new ExitButton("EXIT", metrics, actionExit);
	panelCanvas.add(buttonExit);

	final JFileChooser fileChooser = new FileChooser(new TxtFileFilter(), metrics);
	final AbstractButtonListener actionOpen = new OpenButtonListener(areaOutput, fileChooser, actionRun);
	final AbstractButton buttonOpen = new OpenButton("OPEN", metrics, actionOpen);
	panelCanvas.add(buttonOpen);

	final AbstractLabel labelTitle = new TitleLabel("simpleScript", metrics, panelCanvas);
	panelCanvas.add(labelTitle);
    }

}
