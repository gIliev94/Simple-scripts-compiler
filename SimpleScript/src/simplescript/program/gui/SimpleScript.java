package simplescript.program.gui;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import simplescript.configurator.ConfigurationConstants;
import simplescript.configurator.PrerequisitesConfigurator;
import simplescript.language.scripType.CommandRuntime;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.commands.ICommand;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.language.scripType.processors.ICommandProcessor;
import simplescript.program.utilities.Display;
import simplescript.program.utilities.FrameMetrics;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Main class of the application.
 * 
 * @author Georgi Iliev
 *
 */
public class SimpleScript {

    private static final Logger LOG = Logger.getLogger(SimpleScript.class);

    private JFrame frmSimpleScript;
    private Canvas canvasPanel;
    private JButton btnClear;
    private JButton btnDelete;
    private JFileChooser fileChooser;
    private JTextArea outputArea;
    private Object[] separateCommands;

    /**
     * Launch the application.
     * 
     * @throws InterruptedException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

	Thread.sleep(2000);
	PrerequisitesConfigurator.setup();

	EventQueue.invokeAndWait(new Runnable() {
	    public void run() {
		try {
		    setComponentStyles();
		    SimpleScript window = new SimpleScript();
		    window.frmSimpleScript.setVisible(true);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
			    ConfigurationConstants.NEWLINE + "Unexpected error: " + e.getMessage(), "ERROR",
			    JOptionPane.ERROR_MESSAGE);
		    LOG.error(e.getMessage(), e);
		}
	    }

	    /**
	     * <h1><i>setComponentStyles</i></h1>
	     * <p>
	     * <p>
	     * {@code private void setComponentStyles()}
	     * </p>
	     * Sets the look and feel styles of all visual components to Nimbus
	     * style or Windows classic if the former is not installed. </p>
	     * 
	     * @throws ClassNotFoundException
	     * @throws InstantiationException
	     * @throws IllegalAccessException
	     * @throws UnsupportedLookAndFeelException
	     */
	    private void setComponentStyles() throws ClassNotFoundException, InstantiationException,
		    IllegalAccessException, UnsupportedLookAndFeelException {

		boolean found = false;
		UIManager.LookAndFeelInfo iLAFs[] = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < iLAFs.length; i++) {
		    if (iLAFs[i].getName().equals("Nimbus")) {
			UIManager.setLookAndFeel(iLAFs[i].getClassName());
			found = true;
		    }
		}

		if (!found)
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
	    }
	});
    }

    /**
     * Create the application.
     * 
     * @throws AWTException
     * @throws InterruptedException
     * @throws IOException
     * @throws UnknownCommandException
     */
    public SimpleScript() throws AWTException, InterruptedException, IOException, UnknownCommandException {
	initialize();
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
	FrameMetrics frameMetrics = new FrameMetrics(display.WIDTH, display.HEIGHT);

	JLabel resolutionLabel = new JLabel("Resolution: ");
	resolutionLabel.setToolTipText("Current display resolution");
	resolutionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	resolutionLabel.setForeground(Color.YELLOW);
	resolutionLabel.setBounds(frameMetrics.RESOLUTION_LABEL_X, frameMetrics.RESOLUTION_LABEL_Y,
		frameMetrics.LABELS_WIDTH, frameMetrics.LABELS_HEIGHT);

	JLabel frameSizeLabel = new JLabel("Size: ");
	frameSizeLabel.setToolTipText("Size of the program window");
	frameSizeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	frameSizeLabel.setForeground(Color.YELLOW);
	frameSizeLabel.setBounds(frameMetrics.FRAMESIZE_LABEL_X, frameMetrics.FRAMESIZE_LABEL_Y,
		frameMetrics.LABELS_WIDTH, frameMetrics.LABELS_HEIGHT);

	frmSimpleScript = new JFrame();
	frmSimpleScript.setIconImage(new ImageIcon(this.getClass().getResource("/simpleScriptLogo.png")).getImage());
	frmSimpleScript.setBounds(frameMetrics.X, frameMetrics.Y, frameMetrics.WIDTH, frameMetrics.HEIGHT);

	canvasPanel = new Canvas(frameMetrics.WIDTH, frameMetrics.HEIGHT);
	canvasPanel.setBackground(Color.BLACK);
	frmSimpleScript.getContentPane().add(canvasPanel);

	setupFrame();

	showMetricLabels(display, frameMetrics, resolutionLabel, frameSizeLabel);

	setupFileChooser(frameMetrics);

	JButton btnCompile = new JButton("RUN");
	btnCompile.setToolTipText("Compiles and runs code from a source( .txt ) file");
	btnCompile.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnCompile.setBounds(frameMetrics.COMPILE_BUTTON_X, frameMetrics.COMPILE_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	canvasPanel.add(btnCompile);
	btnCompile.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {

		    ICommandProcessor processor;
		    ICommand[] executableCommands = new Command[separateCommands.length];

		    // Processing and building of commands
		    for (int i = 0; i < separateCommands.length; i++) {
			processor = CommandProcessor.getProcessor((String) separateCommands[i], canvasPanel);
			executableCommands[i] = processor.buildExecutableCommand();
		    }

		    // Running commands
		    CommandRuntime runtime = CommandRuntime.getInstance();
		    runtime.run(executableCommands);

		    showOutMsg("Successful execution!");

		} catch (IOException ioe) {
		    showErr("ERROR", "Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		} catch (AWTException awte) {
		    showErr("ERROR", "Automation\\Threading problem: " + awte.getMessage(), JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		    LOG.error(awte.getMessage(), awte);
		} catch (UnknownCommandException uce) {
		    showErr("ERROR", uce.getMessage() + ConfigurationConstants.NEWLINE + ConfigurationConstants.NEWLINE
			    + "/ REOPEN FILE AFTER YOU FIX THE ERROR /", JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		} catch (NullPointerException npe) {
		    showErr("ERROR", "Missing / not opened file!", JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		    LOG.error(npe.getMessage(), npe);
		} catch (ArrayIndexOutOfBoundsException aobe) {
		    showErr("WARNING", "Empty file, no commands to run!!", JOptionPane.WARNING_MESSAGE);
		    showOutMsg("Compilation failed!");
		    LOG.error(aobe.getMessage(), aobe);
		} catch (NumberFormatException nfe) {
		    showErr("ERROR", "Illegal format for color, use # prefix!", JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		} catch (IllegalArgumentException iae) {
		    showErr("ERROR", "Key mapping not found on local keyboard!" + iae.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		    LOG.error(iae.getMessage(), iae);
		} catch (CommandFormatException cfe) {
		    showErr("ERROR", cfe.getMessage(), JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		}
	    }
	});

	btnClear = new JButton("CLEAR");
	btnClear.setToolTipText("Clears the canvas");
	btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnClear.setBounds(frameMetrics.CLEAR_BUTTON_X, frameMetrics.CLEAR_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	btnClear.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		canvasPanel.fillCanvas(Color.BLACK);
	    }
	});
	canvasPanel.setLayout(null);
	canvasPanel.add(btnClear);

	btnDelete = new JButton("DELETE");
	btnDelete.setToolTipText("Deletes source files( tagged with [src] )");
	btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnDelete.setBounds(frameMetrics.DELETE_BUTTON_X, frameMetrics.DELETE_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	btnDelete.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {

		    // Deletes all files containing the tag - [src]
		    File desktopFolder = new File(ConfigurationConstants.DESKTOP_FOLDER_PATH);
		    Runtime system = Runtime.getRuntime();
		    system.exec("cmd /c del [src]*.txt", null, desktopFolder);

		    showOutMsg("Excercise files removed!");
		} catch (IOException ioe) {
		    showErr("ERROR", "Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Deletion failed!");
		}
	    }
	});
	canvasPanel.add(btnDelete);

	JButton btnExit = new JButton("EXIT");
	btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnExit.setToolTipText("Closes program immediately");
	btnExit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frmSimpleScript.dispose();
	    }
	});
	btnExit.setBounds(frameMetrics.BACK_BUTTON_X, frameMetrics.BACK_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	canvasPanel.add(btnExit);

	JButton btnOpen = new JButton("OPEN");
	btnOpen.setToolTipText("Opens a source( .txt ) file");
	btnOpen.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnOpen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		// Initial validation of loaded source file
		int dialogState = fileChooser.showOpenDialog(fileChooser);

		if (dialogState == JFileChooser.CANCEL_OPTION) {
		    showErr("WARNING", "File selection cancelled!", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		File codeToCompile = fileChooser.getSelectedFile();

		if (!codeToCompile.isFile() || !codeToCompile.getName().endsWith(".txt")) {
		    showErr("ERROR", "Unauthorized file, only .txt permitted!!", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		// Extraction of commands from the source file
		BufferedReader reader = null;
		Vector<String> commandLines = new Vector<String>(16);

		try {
		    String currentLine;
		    reader = new BufferedReader(new FileReader(codeToCompile));

		    while ((currentLine = reader.readLine()) != null) {
			commandLines.add(currentLine);
		    }

		    separateCommands = new String[commandLines.size()];

		    separateCommands = commandLines.toArray();

		    showOutMsg("File sucessfully opened: " + "\"" + codeToCompile.getName() + "\"");

		} catch (FileNotFoundException fnfe) {
		    showErr("ERROR", "File not found: " + codeToCompile.getName(), JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		} catch (IOException ioe) {
		    showErr("ERROR", "Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
			    JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		} catch (NullPointerException npe) {
		    showErr("ERROR", "Missing internal resource value: " + npe.getMessage(), JOptionPane.ERROR_MESSAGE);
		    showOutMsg("Compilation failed!");
		    LOG.error(npe.getMessage(), npe);
		} finally {
		    try {
			if (reader != null)
			    reader.close();
		    } catch (IOException ioe) {
			showErr("ERROR",
				"Error with file/directory: " + ConfigurationConstants.NEWLINE + ioe.getMessage(),
				JOptionPane.ERROR_MESSAGE);
			showOutMsg("Compilation failed!");
		    }
		}
	    }
	});
	btnOpen.setBounds(frameMetrics.OPEN_BUTTON_X, frameMetrics.OPEN_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	canvasPanel.add(btnOpen);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(frameMetrics.TEXT_AREA_X, frameMetrics.TEXT_AREA_Y, frameMetrics.TEXT_AREA_WIDTH,
		frameMetrics.TEXT_AREA_HEIGHT);
	canvasPanel.add(scrollPane);

	outputArea = new JTextArea();
	setupOutputArea(frameMetrics, scrollPane);

	JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("simpleScript");
	setupGoodiesTitle(frameMetrics, lblNewJgoodiesTitle);
    }

    /**
     * <h1><i>showOutMsg</i></h1>
     * <p>
     * {@code private void showOutMsg(String message)}
     * </p>
     * <p>
     * Prints appropriate message to the output area.
     * </p>
     * 
     * @param message
     *            - the message to be shown.
     */
    private void showOutMsg(String message) {
	outputArea.setText(ConfigurationConstants.NEWLINE + message);
    }

    /**
     * <h1><i>showErr</i></h1>
     * <p>
     * {@code  private void showErr(String caption, String message, int warnOrError)}
     * </p>
     * <p>
     * Formats and propagates and error or a warning in a popup pane.
     * </p>
     * 
     * @param caption
     *            - shows the type(warn/err) of the message to the user.
     * @param message
     *            - the message to be shown.
     * @param warnOrError
     *            - constant value diferentiating between WARNING and ERROR.
     */
    private void showErr(String caption, String message, int warnOrError) {
	JOptionPane.showMessageDialog(null, ConfigurationConstants.NEWLINE + message, caption, warnOrError);
    }

    /**
     * <h1><i>setupOutputArea</i></h1>
     * <p>
     * <p>
     * {@code private void setupOutputArea(FrameMetrics frameMetrics,
	    JScrollPane scrollPane)}
     * </p>
     * Sets up the output(status console) area for the user. </p>
     * 
     * @param frameMetrics
     *            - object that holds the relative positions of frame elements.
     * @param scrollPane
     *            - object that induces a scrolling option should there be a
     *            need for it.
     */
    private void setupOutputArea(FrameMetrics frameMetrics, JScrollPane scrollPane) {
	outputArea.setToolTipText("Shows status of current execution");
	outputArea.setEditable(false);
	outputArea.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	outputArea.setBounds(frameMetrics.TEXT_AREA_X, frameMetrics.TEXT_AREA_Y, frameMetrics.TEXT_AREA_WIDTH,
		frameMetrics.TEXT_AREA_HEIGHT);
	scrollPane.setViewportView(outputArea);
    }

    /**
     * <h1><i>setupGoodiesTitle</i></h1>
     * <p>
     * <p>
     * {@code private void setupGoodiesTitle(FrameMetrics frameMetrics,
	    JLabel lblNewJgoodiesTitle)}
     * </p>
     * Sets up the headline at the top of the program screen. </p>
     * 
     * @param frameMetrics
     *            - object that holds the relative positions of frame elements.
     * @param lblNewJgoodiesTitle
     *            - utility object that creates aestesticly pleasing titles.
     */
    private void setupGoodiesTitle(FrameMetrics frameMetrics, JLabel lblNewJgoodiesTitle) {
	lblNewJgoodiesTitle.setFocusable(false);
	lblNewJgoodiesTitle.setIgnoreRepaint(true);
	lblNewJgoodiesTitle.setHorizontalTextPosition(SwingConstants.CENTER);
	lblNewJgoodiesTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	lblNewJgoodiesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewJgoodiesTitle.setLabelFor(canvasPanel);
	lblNewJgoodiesTitle.setFont(new Font("Ravie", Font.PLAIN, 18));
	lblNewJgoodiesTitle.setForeground(Color.YELLOW);
	lblNewJgoodiesTitle.setBounds(178, 7, 249, 14);
	lblNewJgoodiesTitle.setBounds(frameMetrics.CAPTION_LABEL_X, frameMetrics.CAPTION_LABEL_Y,
		frameMetrics.CAPTION_LABEL_WIDTH, frameMetrics.CAPTION_LABEL_HEIGHT);
	canvasPanel.add(lblNewJgoodiesTitle);
    }

    /**
     * <h1><i>setupMetricLabels</i></h1>
     * <p>
     * <p>
     * {@code private void showMetricLabels(Display display, FrameMetrics frameMetrics,
	    JLabel resolutionLabel, JLabel frameSizeLabel)}
     * </p>
     * Sets up labels that show display and frame resolutions. </p>
     * 
     * @param display
     *            - object that describes the dimensions of the display.
     * @param frameMetrics
     *            - object that holds the relative positions of frame elements.
     * @param resolutionLabel
     *            - label for display resolution.
     * @param frameSizeLabel
     *            - label for frame resolution.
     */
    private void showMetricLabels(Display display, FrameMetrics frameMetrics, JLabel resolutionLabel,
	    JLabel frameSizeLabel) {
	String resolution = "Resolution: " + display.getResolution();
	String frameSize = "Window Size: " + frameMetrics.getFrameSize();

	resolutionLabel.setText(resolution);
	frameSizeLabel.setText(frameSize);
	canvasPanel.add(resolutionLabel);
	canvasPanel.add(frameSizeLabel);
    }

    /**
     * <h1><i>setupFileChooser</i></h1>
     * <p>
     * <p>
     * {@code private void setupFileChooser(FrameMetrics frameMetrics)}
     * </p>
     * Sets up file chooser filters, home directories and so on.. </p>
     * 
     * @param frameMetrics
     *            - object that holds the relative positions of frame elements.
     */
    private void setupFileChooser(FrameMetrics frameMetrics) {
	fileChooser = new JFileChooser();

	// Sets desktop as default dir
	File myComputer = fileChooser.getFileSystemView().getParentDirectory(new File("C:\\"));
	File desktop = fileChooser.getFileSystemView().getParentDirectory(myComputer);
	fileChooser.setCurrentDirectory(desktop);

	// Sets filter for .txt sources only
	FileFilter filter = new FileFilter() {

	    public String getDescription() {
		return "Text Documents (*.txt)";
	    }

	    public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith(".txt");
	    }
	};
	fileChooser.addChoosableFileFilter(filter);
	fileChooser.setFileFilter(filter);
	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fileChooser.setPreferredSize(new Dimension(frameMetrics.WIDTH, frameMetrics.HEIGHT - 40));
    }

    /**
     * <h1><i>setupFrame</i></h1>
     * <p>
     * {@code private void setupFrame()}
     * </p>
     * <p>
     * Sets up a group of predefined frame attributes.
     * </p>
     */
    private void setupFrame() {
	frmSimpleScript.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	frmSimpleScript.setTitle("SimpleScript\u2122");
	frmSimpleScript.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmSimpleScript.getContentPane().setLayout(new CardLayout(0, 0));
	frmSimpleScript.setResizable(false);
    }
}
