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

import simplescript.language.scripType.CommandRuntime;
import simplescript.language.scripType.commands.Command;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.program.utilities.Display;
import simplescript.program.utilities.FrameMetrics;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class SimpleScript {

    private JFrame frmSimpleScript;
    private CanvasActions canvasPanel;
    private JButton btnClear;
    private JButton btnDelete;
    private JFileChooser fileChooser;
    private JTextArea outputArea;
    private Object[] separateCommands;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    setComponentStyles();
		    SimpleScript window = new SimpleScript();
		    window.frmSimpleScript.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
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
	    private void setComponentStyles() throws ClassNotFoundException,
		    InstantiationException, IllegalAccessException,
		    UnsupportedLookAndFeelException {

		boolean found = false;
		UIManager.LookAndFeelInfo iLAFs[] = UIManager
			.getInstalledLookAndFeels();

		for (int i = 0; i < iLAFs.length; i++) {
		    if (iLAFs[i].getName().equals("Nimbus")) {
			UIManager.setLookAndFeel(iLAFs[i].getClassName());
			found = true;
		    }
		}

		if (!found)
		    UIManager
			    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
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
    public SimpleScript() throws AWTException, InterruptedException,
	    IOException, UnknownCommandException {
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
    private void initialize() throws AWTException, InterruptedException,
	    IOException, UnknownCommandException {

	Display display = new Display(Toolkit.getDefaultToolkit()
		.getScreenSize());
	FrameMetrics frameMetrics = new FrameMetrics(display.WIDTH,
		display.HEIGHT);

	JLabel resolutionLabel = new JLabel("Resolution: ");
	resolutionLabel.setToolTipText("Current display resolution");
	resolutionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	resolutionLabel.setForeground(Color.YELLOW);
	resolutionLabel.setBounds(frameMetrics.RESOLUTION_LABEL_X,
		frameMetrics.RESOLUTION_LABEL_Y, frameMetrics.LABELS_WIDTH,
		frameMetrics.LABELS_HEIGHT);

	JLabel frameSizeLabel = new JLabel("Size: ");
	frameSizeLabel.setToolTipText("Size of the program window");
	frameSizeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	frameSizeLabel.setForeground(Color.YELLOW);
	frameSizeLabel.setBounds(frameMetrics.FRAMESIZE_LABEL_X,
		frameMetrics.FRAMESIZE_LABEL_Y, frameMetrics.LABELS_WIDTH,
		frameMetrics.LABELS_HEIGHT);

	frmSimpleScript = new JFrame();
	frmSimpleScript.setIconImage(new ImageIcon(this.getClass().getResource(
		"/simpleScriptLogo.png")).getImage());
	frmSimpleScript.setBounds(frameMetrics.X, frameMetrics.Y,
		frameMetrics.WIDTH, frameMetrics.HEIGHT);

	canvasPanel = new CanvasActions(frameMetrics.WIDTH, frameMetrics.HEIGHT);
	frmSimpleScript.getContentPane().add(canvasPanel);

	setupFrame();

	showMetricLabels(display, frameMetrics, resolutionLabel, frameSizeLabel);

	setupFileChooser(frameMetrics);

	JButton btnCompile = new JButton("RUN");
	btnCompile
		.setToolTipText("Compiles and runs code from a source( .txt ) file");
	btnCompile.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnCompile.setBounds(frameMetrics.COMPILE_BUTTON_X,
		frameMetrics.COMPILE_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	canvasPanel.add(btnCompile);
	btnCompile.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {

		    CommandProcessor processor;
		    Command[] executableCommands = new Command[separateCommands.length];

		    // Processing and building of commands
		    for (int i = 0; i < separateCommands.length; i++) {
			processor = CommandProcessor.getProcessor(
				(String) separateCommands[i], canvasPanel);
			executableCommands[i] = processor
				.buildExecutableCommand();
		    }

		    // Running commands
		    CommandRuntime runtime = new CommandRuntime(
			    executableCommands);
		    runtime.run();

		    outputArea.setText(System.getProperty("line.separator")
			    + "Successful execution!");
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(null,
			    System.getProperty("line.separator")
				    + "I/O problem occured!", "ERROR",
			    JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} catch (AWTException e) {
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} catch (UnknownCommandException e) {
		    JOptionPane.showMessageDialog(
			    null,
			    System.getProperty("line.separator")
				    + e.getMessage()
				    + System.getProperty("line.separator")
				    + System.getProperty("line.separator")
				    + "/ REOPEN FILE AFTER YOU FIX THE ERROR /",
			    "ERROR", JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} catch (NullPointerException npe) {
		    JOptionPane.showMessageDialog(null,
			    System.getProperty("line.separator")
				    + "Missing / not opened file!", "ERROR",
			    JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} catch (ArrayIndexOutOfBoundsException aobe) {
		    JOptionPane.showMessageDialog(null,
			    System.getProperty("line.separator")
				    + "Empty file, no commands to run!!",
			    "WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (NumberFormatException nfe) {
		    JOptionPane.showMessageDialog(
			    null,
			    System.getProperty("line.separator")
				    + "Illegal format for color, use # prefix!",
			    "ERROR", JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} catch (IllegalArgumentException iae) {
		    JOptionPane.showMessageDialog(
			    null,
			    System.getProperty("line.separator")
				    + "Incompatible symbol/button on local(Mouse/Keyboard)!",
			    "ERROR", JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		    iae.printStackTrace();
		} catch (CommandFormatException e) {
		    JOptionPane.showMessageDialog(
			    null,
			    System.getProperty("line.separator")
				    + e.getMessage(), "ERROR",
			    JOptionPane.ERROR_MESSAGE);
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		}
	    }
	});

	btnClear = new JButton("CLEAR");
	btnClear.setToolTipText("Clears the canvas");
	btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnClear.setBounds(frameMetrics.CLEAR_BUTTON_X,
		frameMetrics.CLEAR_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
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
	btnDelete.setBounds(frameMetrics.DELETE_BUTTON_X,
		frameMetrics.DELETE_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	btnDelete.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    // Deletes all files containing the tag - [src]
		    Runtime.getRuntime().exec("cmd /c del *[src]*.txt");
		    outputArea.setText(System.getProperty("line.separator")
			    + "Excercise files removed!");
		} catch (IOException e) {
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
	btnExit.setBounds(frameMetrics.BACK_BUTTON_X,
		frameMetrics.BACK_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
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
		    JOptionPane.showMessageDialog(null,
			    System.getProperty("line.separator")
				    + "File selection cancelled!", "WARNING",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}

		File codeToCompile = fileChooser.getSelectedFile();

		if (!codeToCompile.isFile()
			|| !codeToCompile.getName().endsWith(".txt")) {
		    JOptionPane.showMessageDialog(
			    null,
			    System.getProperty("line.separator")
				    + "Unauthorized file, only .txt permitted!!",
			    "ERROR", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		// Extraction of commands from the source file
		BufferedReader reader = null;
		Vector commandLines = new Vector(16);

		try {
		    String currentLine;
		    reader = new BufferedReader(new FileReader(codeToCompile));

		    while ((currentLine = reader.readLine()) != null) {
			commandLines.add(currentLine);
		    }

		    separateCommands = new String[commandLines.size()];

		    separateCommands = commandLines.toArray();

		    outputArea.setText(System.getProperty("line.separator")
			    + "File sucessfully opened: " + "\""
			    + codeToCompile.getName() + "\"");

		} catch (FileNotFoundException fnfe) {
		    outputArea.setText(System.getProperty("line.separator")
			    + "File not found!");
		} catch (IOException ioe) {
		    outputArea.setText(System.getProperty("line.separator")
			    + "File I/O problem occured!");
		} catch (NullPointerException npe) {
		    outputArea.setText(System.getProperty("line.separator")
			    + "Compilation failed!");
		} finally {
		    try {
			if (reader != null)
			    reader.close();
		    } catch (IOException iox) {
		    }
		}
	    }
	});
	btnOpen.setBounds(frameMetrics.OPEN_BUTTON_X,
		frameMetrics.OPEN_BUTTON_Y, frameMetrics.BUTTONS_WIDTH,
		frameMetrics.BUTTONS_HEIGHT);
	canvasPanel.add(btnOpen);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(frameMetrics.TEXT_AREA_X,
		frameMetrics.TEXT_AREA_Y, frameMetrics.TEXT_AREA_WIDTH,
		frameMetrics.TEXT_AREA_HEIGHT);
	canvasPanel.add(scrollPane);

	outputArea = new JTextArea();
	setupOutputArea(frameMetrics, scrollPane);

	JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance()
		.createTitle("simpleScript");
	setupGoodiesTitle(frameMetrics, lblNewJgoodiesTitle);
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
    private void setupOutputArea(FrameMetrics frameMetrics,
	    JScrollPane scrollPane) {
	outputArea.setToolTipText("Shows status of current execution");
	outputArea.setEditable(false);
	outputArea.setBorder(new TitledBorder(null, "STATUS",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	outputArea.setBounds(frameMetrics.TEXT_AREA_X,
		frameMetrics.TEXT_AREA_Y, frameMetrics.TEXT_AREA_WIDTH,
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
    private void setupGoodiesTitle(FrameMetrics frameMetrics,
	    JLabel lblNewJgoodiesTitle) {
	lblNewJgoodiesTitle.setFocusable(false);
	lblNewJgoodiesTitle.setIgnoreRepaint(true);
	lblNewJgoodiesTitle.setHorizontalTextPosition(SwingConstants.CENTER);
	lblNewJgoodiesTitle.setCursor(Cursor
		.getPredefinedCursor(Cursor.HAND_CURSOR));
	lblNewJgoodiesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewJgoodiesTitle.setLabelFor(canvasPanel);
	lblNewJgoodiesTitle.setFont(new Font("Ravie", Font.PLAIN, 18));
	lblNewJgoodiesTitle.setForeground(Color.YELLOW);
	lblNewJgoodiesTitle.setBounds(178, 7, 249, 14);
	lblNewJgoodiesTitle.setBounds(frameMetrics.CAPTION_LABEL_X,
		frameMetrics.CAPTION_LABEL_Y, frameMetrics.CAPTION_LABEL_WIDTH,
		frameMetrics.CAPTION_LABEL_HEIGHT);
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
    private void showMetricLabels(Display display, FrameMetrics frameMetrics,
	    JLabel resolutionLabel, JLabel frameSizeLabel) {
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
	File myComputer = fileChooser.getFileSystemView().getParentDirectory(
		new File("C:\\"));
	File desktop = fileChooser.getFileSystemView().getParentDirectory(
		myComputer);
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
	fileChooser.setPreferredSize(new Dimension(frameMetrics.WIDTH,
		frameMetrics.HEIGHT - 40));
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
	frmSimpleScript.setCursor(Cursor
		.getPredefinedCursor(Cursor.HAND_CURSOR));
	frmSimpleScript.setTitle("SimpleScript\u2122");
	frmSimpleScript.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmSimpleScript.getContentPane().setLayout(new CardLayout(0, 0));
	frmSimpleScript.setResizable(false);
    }
}
